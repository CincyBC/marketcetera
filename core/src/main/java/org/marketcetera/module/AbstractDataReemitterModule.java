package org.marketcetera.module;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/* $License$ */

/**
 * Provides a module framework that emits data it receives.
 *
 * @author <a href="mailto:colin@marketcetera.com">Colin DuPlantis</a>
 * @version $Id$
 * @since $Release$
 */
public abstract class AbstractDataReemitterModule
        extends Module
        implements DataEmitter,DataReceiver
{
    /* (non-Javadoc)
     * @see org.marketcetera.module.DataReceiver#receiveData(org.marketcetera.module.DataFlowID, java.lang.Object)
     */
    @Override
    public void receiveData(DataFlowID inFlowID,
                            Object inData)
            throws ReceiveDataException
    {
        DataEmitterSupport dataEmitterSupport = dataSupport.getIfPresent(inFlowID);
        if(dataEmitterSupport != null) {
            inData = onReceiveData(inData,
                                   dataEmitterSupport);
            if(inData != null) {
                dataEmitterSupport.send(inData);
            }
        }
    }
    /* (non-Javadoc)
     * @see org.marketcetera.module.DataEmitter#requestData(org.marketcetera.module.DataRequest, org.marketcetera.module.DataEmitterSupport)
     */
    @Override
    public void requestData(DataRequest inRequest,
                            DataEmitterSupport inSupport)
            throws RequestDataException
    {
        dataSupport.put(inSupport.getFlowID(),
                        inSupport);
    }
    /* (non-Javadoc)
     * @see org.marketcetera.module.DataEmitter#cancel(org.marketcetera.module.DataFlowID, org.marketcetera.module.RequestID)
     */
    @Override
    public void cancel(DataFlowID inFlowID,
                       RequestID inRequestID)
    {
        dataSupport.invalidate(inFlowID);
    }
    /**
     * Invoked when data is received as part of a data flow.
     *
     * @param inData an <code>Object</code> value
     * @param inDataSupport a <code>DataEmitterSupport</code> value
     * @return an <code>Object</code> value to re-emit
     */
    protected Object onReceiveData(Object inData,
                                   DataEmitterSupport inDataSupport)
    {
        return inData;
    }
    /**
     * Create a new AbstractDataReemitterModule instance.
     *
     * @param inURN a <code>ModuleURN</code> value
     * @param inAutoStart a <code>boolean</code> value
     */
    protected AbstractDataReemitterModule(ModuleURN inURN,
                                          boolean inAutoStart)
    {
        super(inURN,
              inAutoStart);
    }
    /* (non-Javadoc)
     * @see org.marketcetera.module.Module#preStart()
     */
    @Override
    protected void preStart()
            throws ModuleException
    {
        dataSupport.invalidateAll();
    }
    /* (non-Javadoc)
     * @see org.marketcetera.module.Module#preStop()
     */
    @Override
    protected void preStop()
            throws ModuleException
    {
    }
    /**
     * if wired into a multi-module flow, this object will assist in passing data to the next object in the flow
     */
    private final Cache<DataFlowID,DataEmitterSupport> dataSupport = CacheBuilder.newBuilder().build();
}
