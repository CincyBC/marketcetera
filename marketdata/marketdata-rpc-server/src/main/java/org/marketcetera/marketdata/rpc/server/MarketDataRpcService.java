package org.marketcetera.marketdata.rpc.server;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Set;

import javax.annotation.concurrent.GuardedBy;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.marketcetera.admin.service.AuthorizationService;
import org.marketcetera.event.Event;
import org.marketcetera.marketdata.Capability;
import org.marketcetera.marketdata.MarketDataListener;
import org.marketcetera.marketdata.MarketDataPermissions;
import org.marketcetera.marketdata.MarketDataRpcUtil;
import org.marketcetera.marketdata.core.rpc.MarketDataRpc;
import org.marketcetera.marketdata.core.rpc.MarketDataRpc.AvailableCapabilityRequest;
import org.marketcetera.marketdata.core.rpc.MarketDataRpc.AvailableCapabilityResponse;
import org.marketcetera.marketdata.core.rpc.MarketDataRpc.CancelRequest;
import org.marketcetera.marketdata.core.rpc.MarketDataRpc.CancelResponse;
import org.marketcetera.marketdata.core.rpc.MarketDataRpc.MarketDataRequest;
import org.marketcetera.marketdata.core.rpc.MarketDataRpc.SnapshotPageRequest;
import org.marketcetera.marketdata.core.rpc.MarketDataRpc.SnapshotPageResponse;
import org.marketcetera.marketdata.core.rpc.MarketDataRpc.SnapshotRequest;
import org.marketcetera.marketdata.core.rpc.MarketDataRpc.SnapshotResponse;
import org.marketcetera.marketdata.core.rpc.MarketDataRpcServiceGrpc;
import org.marketcetera.marketdata.core.rpc.MarketDataRpcServiceGrpc.MarketDataRpcServiceImplBase;
import org.marketcetera.marketdata.service.MarketDataService;
import org.marketcetera.rpc.base.BaseRpc.HeartbeatRequest;
import org.marketcetera.rpc.base.BaseRpc.HeartbeatResponse;
import org.marketcetera.rpc.base.BaseRpc.LoginRequest;
import org.marketcetera.rpc.base.BaseRpc.LoginResponse;
import org.marketcetera.rpc.base.BaseRpc.LogoutRequest;
import org.marketcetera.rpc.base.BaseRpc.LogoutResponse;
import org.marketcetera.rpc.base.BaseUtil;
import org.marketcetera.rpc.server.AbstractRpcService;
import org.marketcetera.trade.TradeMessageListener;
import org.marketcetera.util.log.SLF4JLoggerProxy;
import org.marketcetera.util.ws.ContextClassProvider;
import org.marketcetera.util.ws.stateful.SessionHolder;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

/* $License$ */

/**
 * Provides an RPC market data service implementation.
 *
 * @author <a href="mailto:colin@marketcetera.com">Colin DuPlantis</a>
 * @version $Id: MarketDataRpcService.java 17251 2016-09-08 23:18:29Z colin $
 * @since $Release$
 */
public class MarketDataRpcService<SessionClazz>
        extends AbstractRpcService<SessionClazz,MarketDataRpcServiceGrpc.MarketDataRpcServiceImplBase>
{
    /* (non-Javadoc)
     * @see org.marketcetera.rpc.server.AbstractRpcService#start()
     */
    @Override
    public void start()
            throws Exception
    {
        Validate.notNull(marketDataService,
                         "Market data service required");
        service = new Service();
        synchronized(contextLock) {
            context = JAXBContext.newInstance(contextClassProvider==null?new Class<?>[0]:contextClassProvider.getContextClasses());
            marshaller = context.createMarshaller();
            unmarshaller = context.createUnmarshaller();
        }
        super.start();
    }
    /**
     * Get the market data service service value.
     *
     * @return a <code>MarketDataService</code> value
     */
    public MarketDataService getServiceAdapter()
    {
        return marketDataService;
    }
    /**
     * Sets the market data service value.
     *
     * @param inMarketDataService a <code>MarketDataService</code> value
     */
    public void setServiceAdapter(MarketDataService inMarketDataService)
    {
        marketDataService = inMarketDataService;
    }
    /**
     * Get the contextClassProvider value.
     *
     * @return a <code>ContextClassProvider</code> value
     */
    public ContextClassProvider getContextClassProvider()
    {
        return contextClassProvider;
    }
    /**
     * Sets the contextClassProvider value.
     *
     * @param inContextClassProvider a <code>ContextClassProvider</code> value
     */
    public void setContextClassProvider(ContextClassProvider inContextClassProvider)
    {
        contextClassProvider = inContextClassProvider;
    }
    /* (non-Javadoc)
     * @see org.marketcetera.rpc.server.AbstractRpcService#getServiceDescription()
     */
    @Override
    protected String getServiceDescription()
    {
        return description;
    }
    /* (non-Javadoc)
     * @see org.marketcetera.rpc.server.AbstractRpcService#getService()
     */
    @Override
    protected MarketDataRpcServiceImplBase getService()
    {
        return service;
    }
    /**
     * Marketdata RPC Service implementation.
     *
     * @author <a href="mailto:colin@marketcetera.com">Colin DuPlantis</a>
     * @version $Id: MarketDataRpcService.java 17251 2016-09-08 23:18:29Z colin $
     * @since $Release$
     */
    private class Service
            extends MarketDataRpcServiceGrpc.MarketDataRpcServiceImplBase
    {
        /* (non-Javadoc)
         * @see org.marketcetera.marketdata.core.rpc.MarketDataRpcServiceGrpc.MarketDataRpcServiceImplBase#login(org.marketcetera.rpc.base.BaseRpc.LoginRequest, io.grpc.stub.StreamObserver)
         */
        @Override
        public void login(LoginRequest inRequest,
                          StreamObserver<LoginResponse> inResponseObserver)
        {
            MarketDataRpcService.this.doLogin(inRequest,
                                              inResponseObserver);
        }
        /* (non-Javadoc)
         * @see org.marketcetera.marketdata.core.rpc.MarketDataRpcServiceGrpc.MarketDataRpcServiceImplBase#logout(org.marketcetera.rpc.base.BaseRpc.LogoutRequest, io.grpc.stub.StreamObserver)
         */
        @Override
        public void logout(LogoutRequest inRequest,
                           StreamObserver<LogoutResponse> inResponseObserver)
        {
            MarketDataRpcService.this.doLogout(inRequest,
                                               inResponseObserver);
        }
        /* (non-Javadoc)
         * @see org.marketcetera.marketdata.core.rpc.MarketDataRpcServiceGrpc.MarketDataRpcServiceImplBase#heartbeat(org.marketcetera.rpc.base.BaseRpc.HeartbeatRequest, io.grpc.stub.StreamObserver)
         */
        @Override
        public void heartbeat(HeartbeatRequest inRequest,
                              StreamObserver<HeartbeatResponse> inResponseObserver)
        {
            MarketDataRpcService.this.doHeartbeat(inRequest,
                                                  inResponseObserver);
        }
        /* (non-Javadoc)
         * @see org.marketcetera.marketdata.core.rpc.MarketDataRpcServiceGrpc.MarketDataRpcServiceImplBase#request(org.marketcetera.marketdata.core.rpc.MarketdataRpc.MarketDataRequest, io.grpc.stub.StreamObserver)
         */
        @Override
        public void request(MarketDataRequest inRequest,
                            StreamObserver<MarketDataRpc.EventsResponse> inResponseObserver)
        {
            try {
                SessionHolder<SessionClazz> sessionHolder = validateAndReturnSession(inRequest.getSessionId());
                SLF4JLoggerProxy.trace(MarketDataRpcService.this,
                                       "Received market data request {}",
                                       inRequest);
                authzService.authorize(sessionHolder.getUser(),
                                       MarketDataPermissions.RequestMarketDataAction.name());
                String requestId = buildRequestId(inRequest.getSessionId(),
                                                  inRequest.getListenerId());
                BaseUtil.AbstractServerListenerProxy<?> marketDataListenerProxy = listenerProxiesById.getIfPresent(requestId);
                if(marketDataListenerProxy == null) {
                    marketDataListenerProxy = new MarketDataListenerProxy(requestId,
                                                                          inRequest.getListenerId(),
                                                                          inResponseObserver);
                    listenerProxiesById.put(marketDataListenerProxy.getId(),
                                            marketDataListenerProxy);
                    marketDataService.request(MarketDataRpcUtil.getMarketDataRequest(inRequest.getRequest(),
                                                                                     requestId),
                                              (MarketDataListener)marketDataListenerProxy);
                }
            } catch (Exception e) {
                inResponseObserver.onError(e);
                SLF4JLoggerProxy.warn(MarketDataRpcService.this,
                                      e);
            }
        }
        /* (non-Javadoc)
         * @see org.marketcetera.marketdata.core.rpc.MarketDataRpcServiceGrpc.MarketDataRpcServiceImplBase#cancel(org.marketcetera.marketdata.core.rpc.MarketdataRpc.CancelRequest, io.grpc.stub.StreamObserver)
         */
        @Override
        public void cancel(CancelRequest inRequest,
                           StreamObserver<CancelResponse> inResponseObserver)
        {
            try {
                validateAndReturnSession(inRequest.getSessionId());
                MarketDataRpc.CancelResponse.Builder responseBuilder = MarketDataRpc.CancelResponse.newBuilder();
                String requestId = buildRequestId(inRequest.getSessionId(),
                                                  inRequest.getListenerId());
                BaseUtil.AbstractServerListenerProxy<?> marketDataListenerProxy = listenerProxiesById.getIfPresent(requestId);
                listenerProxiesById.invalidate(requestId);
                marketDataService.cancel(inRequest.getListenerId());
                if(marketDataListenerProxy != null) {
                    marketDataListenerProxy.close();
                }
                inResponseObserver.onNext(responseBuilder.build());
                inResponseObserver.onCompleted();
            } catch (Exception e) {
                if(e instanceof StatusRuntimeException) {
                    throw (StatusRuntimeException)e;
                }
                throw new StatusRuntimeException(Status.INVALID_ARGUMENT.withCause(e).withDescription(ExceptionUtils.getRootCauseMessage(e)));
            }
        }
        /* (non-Javadoc)
         * @see org.marketcetera.marketdata.core.rpc.MarketDataRpcServiceGrpc.MarketDataRpcServiceImplBase#getSnapshot(org.marketcetera.marketdata.core.rpc.MarketdataRpc.SnapshotRequest, io.grpc.stub.StreamObserver)
         */
        @Override
        public void getSnapshot(SnapshotRequest inRequest,
                                StreamObserver<SnapshotResponse> inResponseObserver)
        {
            try {
                validateAndReturnSession(inRequest.getSessionId());
                MarketDataRpc.SnapshotResponse.Builder responseBuilder = MarketDataRpc.SnapshotResponse.newBuilder();
//                Instrument instrument = unmarshall(inRequest.getInstrument().getPayload());
//                Content content = Content.valueOf(inRequest.getContent().name());
//                Deque<Event> events = marketDataService.getSnapshot(instrument,
//                                                                 content,
//                                                                 inRequest.getProvider());
//                for(Event event : events) {
//                    responseBuilder.addPayload(marshal(event));
//                }
                MarketDataRpc.SnapshotResponse response = responseBuilder.build();
                inResponseObserver.onNext(response);
                inResponseObserver.onCompleted();
            } catch (Exception e) {
                if(e instanceof StatusRuntimeException) {
                    throw (StatusRuntimeException)e;
                }
                throw new StatusRuntimeException(Status.INVALID_ARGUMENT.withCause(e).withDescription(ExceptionUtils.getRootCauseMessage(e)));
            }
        }
        /* (non-Javadoc)
         * @see org.marketcetera.marketdata.core.rpc.MarketDataRpcServiceGrpc.MarketDataRpcServiceImplBase#getSnapshotPage(org.marketcetera.marketdata.core.rpc.MarketdataRpc.SnapshotPageRequest, io.grpc.stub.StreamObserver)
         */
        @Override
        public void getSnapshotPage(SnapshotPageRequest inRequest,
                                    StreamObserver<SnapshotPageResponse> inResponseObserver)
        {
            try {
                validateAndReturnSession(inRequest.getSessionId());
                MarketDataRpc.SnapshotPageResponse.Builder responseBuilder = MarketDataRpc.SnapshotPageResponse.newBuilder();
//                Instrument instrument = unmarshall(inRequest.getInstrument().getPayload());
//                Content content = Content.valueOf(inRequest.getContent().name());
//                Deque<Event> events = marketDataService.getSnapshotPage(instrument,
//                                                                     content,
//                                                                     inRequest.getProvider(),
//                                                                     new PageRequest(inRequest.getPage().getPage(),
//                                                                                     inRequest.getPage().getSize()));
//                for(Event event : events) {
//                    responseBuilder.addPayload(marshal(event));
//                }
                MarketDataRpc.SnapshotPageResponse response = responseBuilder.build();
                inResponseObserver.onNext(response);
                inResponseObserver.onCompleted();
            } catch (Exception e) {
                if(e instanceof StatusRuntimeException) {
                    throw (StatusRuntimeException)e;
                }
                throw new StatusRuntimeException(Status.INVALID_ARGUMENT.withCause(e).withDescription(ExceptionUtils.getRootCauseMessage(e)));
            }
        }
        /* (non-Javadoc)
         * @see org.marketcetera.marketdata.core.rpc.MarketDataRpcServiceGrpc.MarketDataRpcServiceImplBase#getAvailableCapability(org.marketcetera.marketdata.core.rpc.MarketdataRpc.AvailableCapabilityRequest, io.grpc.stub.StreamObserver)
         */
        @Override
        public void getAvailableCapability(AvailableCapabilityRequest inRequest,
                                           StreamObserver<AvailableCapabilityResponse> inResponseObserver)
        {
            try {
                validateAndReturnSession(inRequest.getSessionId());
                MarketDataRpc.AvailableCapabilityResponse.Builder responseBuilder = MarketDataRpc.AvailableCapabilityResponse.newBuilder();
                Set<Capability> events = marketDataService.getAvailableCapability();
                for(Capability event : events) {
                    responseBuilder.addCapability(MarketDataRpc.ContentAndCapability.valueOf(event.name()));
                }
                MarketDataRpc.AvailableCapabilityResponse response = responseBuilder.build();
                inResponseObserver.onNext(response);
                inResponseObserver.onCompleted();
            } catch (Exception e) {
                if(e instanceof StatusRuntimeException) {
                    throw (StatusRuntimeException)e;
                }
                throw new StatusRuntimeException(Status.INVALID_ARGUMENT.withCause(e).withDescription(ExceptionUtils.getRootCauseMessage(e)));
            }
        }
        /**
         * Build a request id from the given attributes.
         *
         * @param inSessionId a <code>String</code> value
         * @param inListenerId a <code>String</code> value
         * @return a <code>String</code> value
         */
        private String buildRequestId(String inSessionId,
                                      String inListenerId)
        {
            return new StringBuilder().append(inSessionId).append('-').append(inListenerId).toString();
        }
    }
    /**
     * Marshals the given object to an XML stream.
     *
     * @param inObject an <code>Object</code> value
     * @return a <code>String</code> value
     * @throws JAXBException if an error occurs marshalling the data
     */
    private String marshal(Object inObject)
            throws JAXBException
    {
        StringWriter output = new StringWriter();
        synchronized(contextLock) {
            marshaller.marshal(inObject,
                               output);
        }
        return output.toString();
    }
    /**
     * Unmarshals an object from the given XML stream.
     *
     * @param inData a <code>String</code> value
     * @return a <code>Clazz</code> value
     * @throws JAXBException if an error occurs unmarshalling the data
     */
    @SuppressWarnings({ "unchecked" })
    private <Clazz> Clazz unmarshall(String inData)
            throws JAXBException
    {
        synchronized(contextLock) {
            return (Clazz)unmarshaller.unmarshal(new StringReader(inData));
        }
    }
    /**
     * Wraps a {@link TradeMessageListener} with the RPC call from the client.
     *
     * @author <a href="mailto:colin@marketcetera.com">Colin DuPlantis</a>
     * @version $Id$
     * @since $Release$
     */
    private static class MarketDataListenerProxy
            extends BaseUtil.AbstractServerListenerProxy<MarketDataRpc.EventsResponse>
            implements MarketDataListener
    {
        /* (non-Javadoc)
         * @see org.marketcetera.marketdata.MarketDataListener#receiveMarketData(org.marketcetera.event.Event)
         */
        @Override
        public void receiveMarketData(Event inEvent)
        {
            MarketDataRpcUtil.setEvent(inEvent,
                                       responseBuilder);
            responseBuilder.setListenerId(listenerId);
            MarketDataRpc.EventsResponse response = responseBuilder.build();
            SLF4JLoggerProxy.trace(MarketDataRpcService.class,
                                   "{} received event {}, sending {}",
                                   getId(),
                                   inEvent,
                                   response);
            // TODO does the user have permissions (including supervisor) to view this event?
            getObserver().onNext(response);
            responseBuilder.clear();
        }
        /**
         * Create a new MarketDataListenerProxy instance.
         *
         * @param inRequestId a <code>String</code> value
         * @param inListenerId a <code>String</code> value
         * @param inObserver a <code>StreamObserver&lt;MarketDataListenerResponse&gt;</code> value
         */
        private MarketDataListenerProxy(String inRequestId,
                                        String inListenerId,
                                        StreamObserver<MarketDataRpc.EventsResponse> inObserver)
        {
            super(inRequestId,
                  inObserver);
            listenerId = inListenerId;
        }
        /**
         * client-side id
         */
        private final String listenerId;
        /**
         * builder used to construct messages
         */
        private final MarketDataRpc.EventsResponse.Builder responseBuilder = MarketDataRpc.EventsResponse.newBuilder();
    }
    /**
     * provides context classes for marshalling/unmarshalling, may be <code>null</code>
     */
    private ContextClassProvider contextClassProvider;
    /**
     * guards access to JAXB context objects
     */
    private final Object contextLock = new Object();
    /**
     * context used to serialize and unserialize messages as necessary
     */
    @GuardedBy("contextLock")
    private JAXBContext context;
    /**
     * marshals messages
     */
    @GuardedBy("contextLock")
    private Marshaller marshaller;
    /**
     * unmarshals messages
     */
    @GuardedBy("contextLock")
    private Unmarshaller unmarshaller;
    /**
     * provides access to market data services
     */
    @Autowired
    private MarketDataService marketDataService;
    /**
     * provides authorization services
     */
    @Autowired
    private AuthorizationService authzService;
    /**
     * service instance
     */
    private Service service;
    /**
     * description of this service
     */
    private final static String description = "Marketdata RPC Service";
    /**
     * holds trade message listeners by id
     */
    private final Cache<String,BaseUtil.AbstractServerListenerProxy<?>> listenerProxiesById = CacheBuilder.newBuilder().build();
}
