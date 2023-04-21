//
// this file is automatically generated
//
package org.marketcetera.strategy;

import java.util.Optional;

import org.marketcetera.admin.UserFactory;
import org.marketcetera.admin.rpc.AdminRpcUtil;
import org.marketcetera.core.Preserve;
import org.marketcetera.core.notifications.INotification;
import org.marketcetera.rpc.base.BaseRpcUtil;
import org.marketcetera.strategy.events.SimpleStrategyMessageEvent;
import org.marketcetera.strategy.events.SimpleStrategyStartFailedEvent;
import org.marketcetera.strategy.events.SimpleStrategyStartedEvent;
import org.marketcetera.strategy.events.SimpleStrategyStatusChangedEvent;
import org.marketcetera.strategy.events.SimpleStrategyStoppedEvent;
import org.marketcetera.strategy.events.SimpleStrategyUnloadedEvent;
import org.marketcetera.strategy.events.SimpleStrategyUploadFailedEvent;
import org.marketcetera.strategy.events.SimpleStrategyUploadSucceededEvent;
import org.marketcetera.strategy.events.StrategyEvent;
import org.marketcetera.strategy.events.StrategyMessageEvent;
import org.marketcetera.strategy.events.StrategyStartFailedEvent;
import org.marketcetera.strategy.events.StrategyStartedEvent;
import org.marketcetera.strategy.events.StrategyStatusChangedEvent;
import org.marketcetera.strategy.events.StrategyStoppedEvent;
import org.marketcetera.strategy.events.StrategyUnloadedEvent;
import org.marketcetera.strategy.events.StrategyUploadFailedEvent;
import org.marketcetera.strategy.events.StrategyUploadSucceededEvent;

/* $License$ */

/**
 * Provides common behavior for Strategy services.
 *
 * @author <a href="mailto:colin@marketcetera.com">Colin DuPlantis</a>
 * @version $Id$
 * @since $Release$
 */
@Preserve
public abstract class StrategyRpcUtil
{
    /**
     * Get the RPC object from the given value.
     *
     * @param inStrategyInstance a <code>StrategyInstance</code> value
     * @return an Optional&lt;StrategyTypesRpc.StrategyInstance&gt; value
     */
    public static Optional<StrategyTypesRpc.StrategyInstance> getRpcStrategyInstance(StrategyInstance inStrategyInstance)
    {
        if(inStrategyInstance == null) {
            return Optional.empty();
        }
        StrategyTypesRpc.StrategyInstance.Builder builder = StrategyTypesRpc.StrategyInstance.newBuilder();
        AdminRpcUtil.getRpcUser(inStrategyInstance.getUser()).ifPresent(value->builder.setUser(value));
        if(inStrategyInstance.getName() != null) {
            builder.setName(inStrategyInstance.getName());
        }
        if(inStrategyInstance.getFilename() != null) {
            builder.setFilename(inStrategyInstance.getFilename());
        }
        if(inStrategyInstance.getHash() != null) {
            builder.setHash(inStrategyInstance.getHash());
        }
        if(inStrategyInstance.getNonce() != null) {
            builder.setNonce(inStrategyInstance.getNonce());
        }
        org.marketcetera.rpc.base.BaseRpcUtil.getTimestampValue(inStrategyInstance.getStarted()).ifPresent(value->builder.setStarted(value));
        getRpcStrategyStatus(inStrategyInstance.getStatus()).ifPresent(value->builder.setStatus(value));
        return Optional.of(builder.build());
    }
    /**
     * Get the object from the given RPC value.
     *
     * @param inStrategyStatus an <code>StrategyTypesRpc.StrategyStatus</code> value
     * @return an Optional&lt;StrategyStatus&gt; value
     */
    public static Optional<StrategyStatus> getStrategyStatus(StrategyTypesRpc.StrategyStatus inStrategyStatus)
    {
        if(inStrategyStatus == null) {
            return Optional.empty();
        }
        return Optional.of(StrategyStatus.values()[inStrategyStatus.getNumber()]);
    }
    /**
     * Get the RPC value from the given object.
     *
     * @param inRpcStrategyStatus a <code>StrategyStatus</code> value
     * @return an Optional&lt;StrategyTypesRpc.StrategyStatus&gt; value
     */
    public static Optional<StrategyTypesRpc.StrategyStatus> getRpcStrategyStatus(StrategyStatus inRpcStrategyStatus)
    {
        if(inRpcStrategyStatus == null) {
            return Optional.empty();
        }
        return Optional.of(StrategyTypesRpc.StrategyStatus.forNumber(inRpcStrategyStatus.ordinal()));
    }
    /**
     * Get the RPC object from the given value.
     *
     * @param inStrategyMessage a <code>StrategyMessage</code> value
     * @return an Optional&lt;StrategyTypesRpc.StrategyMessage&gt; value
     */
    public static Optional<StrategyTypesRpc.StrategyMessage> getRpcStrategyMessage(StrategyMessage inStrategyMessage)
    {
        if(inStrategyMessage == null) {
            return Optional.empty();
        }
        StrategyTypesRpc.StrategyMessage.Builder builder = StrategyTypesRpc.StrategyMessage.newBuilder();
        StrategyRpcUtil.getRpcStrategyInstance(inStrategyMessage.getStrategyInstance()).ifPresent(value->builder.setStrategyInstance(value));
        getRpcStrategyMessageSeverity(inStrategyMessage.getSeverity()).ifPresent(value->builder.setSeverity(value));
        BaseRpcUtil.getTimestampValue(inStrategyMessage.getMessageTimestamp()).ifPresent(rpcTimestamp -> builder.setMessageTimestamp(rpcTimestamp));
        if(inStrategyMessage.getMessage() != null) {
            builder.setMessage(inStrategyMessage.getMessage());
        }
        builder.setStrategyMessageId(inStrategyMessage.getStrategyMessageId());
        return Optional.of(builder.build());
    }
    /**
     * Get the object from the given RPC value.
     *
     * @param inStrategyMessage an <code>StrategyTypesRpc.StrategyMessage</code> value
     * @param inStrategyMessageFactory an <code>StrategyMessageFactory</code> value
     * @param inStrategyInstanceFactory an <code>StrategyInstanceFactory</code> value
     * @param inUserFactory an <code>UserFactory</code> value
     * @return an Optional&lt;StrategyMessage&gt; value
     */
    public static Optional<StrategyMessage> getStrategyMessage(StrategyTypesRpc.StrategyMessage inStrategyMessage,
                                                               StrategyMessageFactory inStrategyMessageFactory,
                                                               StrategyInstanceFactory inStrategyInstanceFactory,
                                                               UserFactory inUserFactory)
    {
        if(inStrategyMessage == null) {
            return Optional.empty();
        }
        StrategyMessage strategyMessage = inStrategyMessageFactory.create();
        StrategyRpcUtil.getStrategyInstance(inStrategyMessage.getStrategyInstance(),inStrategyInstanceFactory,inUserFactory).ifPresent(value->strategyMessage.setStrategyInstance(value));
        getStrategyMessageSeverity(inStrategyMessage.getSeverity()).ifPresent(value->strategyMessage.setSeverity(value));
        BaseRpcUtil.getDateValue(inStrategyMessage.getMessageTimestamp()).ifPresent(messageTimestamp -> strategyMessage.setMessageTimestamp(messageTimestamp));
        strategyMessage.setMessage(inStrategyMessage.getMessage());
        ((SimpleStrategyMessage)strategyMessage).setStrategyMessageId(inStrategyMessage.getStrategyMessageId());
        return Optional.of(strategyMessage);
    }
    /**
     * Get the object from the given RPC value.
     *
     * @param inStrategyInstance an <code>StrategyTypesRpc.StrategyInstance</code> value
     * @param inStrategyInstanceFactory an <code>StrategyInstanceFactory</code> value
     * @param inUserFactory an <code>UserFactory</code> value
     * @return an Optional&lt;StrategyInstance&gt; value
     */
    public static Optional<StrategyInstance> getStrategyInstance(StrategyTypesRpc.StrategyInstance inStrategyInstance,
                                                                 StrategyInstanceFactory inStrategyInstanceFactory,
                                                                 UserFactory inUserFactory)
    {
        if(inStrategyInstance == null) {
            return Optional.empty();
        }
        StrategyInstance strategyInstance = inStrategyInstanceFactory.create();
        AdminRpcUtil.getUser(inStrategyInstance.getUser(),inUserFactory).ifPresent(value->strategyInstance.setUser(value));
        strategyInstance.setName(inStrategyInstance.getName());
        strategyInstance.setFilename(inStrategyInstance.getFilename());
        strategyInstance.setHash(inStrategyInstance.getHash());
        strategyInstance.setNonce(inStrategyInstance.getNonce());
        org.marketcetera.rpc.base.BaseRpcUtil.getDateValue(inStrategyInstance.getStarted()).ifPresent(value->strategyInstance.setStarted(value));
        getStrategyStatus(inStrategyInstance.getStatus()).ifPresent(value->strategyInstance.setStatus(value));
        return Optional.of(strategyInstance);
    }
    /**
     * Get the object from the given RPC value.
     *
     * @param inSeverity an <code>StrategyTypesRpc.StrategyMessageSeverity</code> value
     * @return an Optional&lt;INotification.Severity&gt; value
     */
    public static Optional<INotification.Severity> getStrategyMessageSeverity(StrategyTypesRpc.StrategyMessageSeverity inSeverity)
    {
        if(inSeverity == null) {
            return Optional.empty();
        }
        return Optional.of(INotification.Severity.values()[inSeverity.getNumber()]);
    }
    /**
     * Get the RPC value from the given object.
     *
     * @param inRpcSeverity a <code>INotification.Severity</code> value
     * @return an Optional&lt;StrategyTypesRpc.StrategyMessageSeverity&gt; value
     */
    public static Optional<StrategyTypesRpc.StrategyMessageSeverity> getRpcStrategyMessageSeverity(INotification.Severity inRpcSeverity)
    {
        if(inRpcSeverity == null) {
            return Optional.empty();
        }
        return Optional.of(StrategyTypesRpc.StrategyMessageSeverity.forNumber(inRpcSeverity.ordinal()));
    }
    /**
     * Write the given strategy event to the given response builder.
     *
     * @param inStrategyEvent a <code>StrategyEvent</code> value
     * @param inResponseBuilder a <code>StrategyRpc.StrategyEventListenerResponse.Builder</code> value
     */
    public static void setStrategyEvent(StrategyEvent inStrategyEvent,
                                        StrategyRpc.StrategyEventListenerResponse.Builder inResponseBuilder)
    {
        StrategyTypesRpc.StrategyEvent.Builder rpcEventBuilder = StrategyTypesRpc.StrategyEvent.newBuilder();
        rpcEventBuilder.setEventType(inStrategyEvent.getClass().getSimpleName());
        if(inStrategyEvent.getStrategyInstance() != null) {
            getRpcStrategyInstance(inStrategyEvent.getStrategyInstance()).ifPresent(rpcStrategyInstance -> rpcEventBuilder.setStrategyInstance(rpcStrategyInstance));
        }
        rpcEventBuilder.setEventType(inStrategyEvent.getClass().getSimpleName());
        if(inStrategyEvent instanceof StrategyUploadFailedEvent) {
            StrategyUploadFailedEvent failedEvent = (StrategyUploadFailedEvent)inStrategyEvent;
            rpcEventBuilder.setMessage(failedEvent.getErrorMessage());
        } else if(inStrategyEvent instanceof StrategyUploadSucceededEvent) {
        } else if(inStrategyEvent instanceof StrategyUnloadedEvent) {
        } else if(inStrategyEvent instanceof StrategyStartedEvent) {
        } else if(inStrategyEvent instanceof StrategyStartFailedEvent) {
            StrategyStartFailedEvent failedEvent = (StrategyStartFailedEvent)inStrategyEvent;
            if(failedEvent.getErrorMessage() != null) {
                rpcEventBuilder.setMessage(failedEvent.getErrorMessage());
            }
        } else if(inStrategyEvent instanceof StrategyStoppedEvent) {
        } else if(inStrategyEvent instanceof StrategyStatusChangedEvent) {
            StrategyStatusChangedEvent statusChangedEvent = (StrategyStatusChangedEvent)inStrategyEvent;
            getRpcStrategyStatus(statusChangedEvent.getNewValue()).ifPresent(rpcStrategyStatus -> rpcEventBuilder.setNewStatusValue(rpcStrategyStatus));
            getRpcStrategyStatus(statusChangedEvent.getOldValue()).ifPresent(rpcStrategyStatus -> rpcEventBuilder.setOldStatusValue(rpcStrategyStatus));
        } else if(inStrategyEvent instanceof StrategyMessageEvent) {
            StrategyMessageEvent messageEvent = (StrategyMessageEvent)inStrategyEvent;
            rpcEventBuilder.setMessage(messageEvent.getStrategyMessage().getMessage());
            getRpcStrategyMessageSeverity(messageEvent.getStrategyMessage().getSeverity()).ifPresent(rpcSeverity -> rpcEventBuilder.setSeverity(rpcSeverity));
            BaseRpcUtil.getTimestampValue(messageEvent.getStrategyMessage().getMessageTimestamp()).ifPresent(rpcTimestamp -> rpcEventBuilder.setMessageTimestamp(rpcTimestamp));
            rpcEventBuilder.setId(messageEvent.getStrategyMessage().getStrategyMessageId());
        } else {
            throw new UnsupportedOperationException("Unexpected strategy event type: " + inStrategyEvent.getClass().getSimpleName());
        }
        inResponseBuilder.setEvent(rpcEventBuilder.build());
    }
    /**
     * Get the strategy event from the given RPC event, if possible.
     *
     * @param inResponse a <code>StrategyRpc.StrategyEventListenerResponse</code> value 
     * @param inStrategyInstanceFactory a <code>StrategyInstanceFactory</code> value
     * @param inUserFactory a <code>UserFactory</code> value
     * @return a <code>StrategyEvent</code> or <code>null</code>
     */
    public static StrategyEvent getStrategyEvent(StrategyRpc.StrategyEventListenerResponse inResponse,
                                                 StrategyInstanceFactory inStrategyInstanceFactory,
                                                 UserFactory inUserFactory)
    {
        if(inResponse.hasEvent()) {
            StrategyTypesRpc.StrategyEvent rpcEvent = inResponse.getEvent();
            if(rpcEvent.hasStrategyInstance()) {
                Optional<? extends StrategyInstance> strategyInstanceOption = getStrategyInstance(rpcEvent.getStrategyInstance(),
                                                                                                  inStrategyInstanceFactory,
                                                                                                  inUserFactory);
                if(strategyInstanceOption.isPresent()) {
                    StrategyInstance strategyInstance = strategyInstanceOption.get();
                    switch(rpcEvent.getEventType()) {
                        case "SimpleStrategyMessageEvent":
                            SimpleStrategyMessageEvent messageEvent = new SimpleStrategyMessageEvent();
                            SimpleStrategyMessage message = new SimpleStrategyMessage();
                            message.setMessage(rpcEvent.getMessage());
                            message.setStrategyMessageId(rpcEvent.getId());
                            getStrategyMessageSeverity(rpcEvent.getSeverity()).ifPresent(severity -> message.setSeverity(severity));
                            BaseRpcUtil.getDateValue(rpcEvent.getMessageTimestamp()).ifPresent(timestamp -> message.setMessageTimestamp(timestamp));
                            message.setStrategyInstance(strategyInstance);
                            messageEvent.setStrategyMessage(message);
                            return messageEvent;
                        case "SimpleStrategyStartedEvent":
                            SimpleStrategyStartedEvent startEvent = new SimpleStrategyStartedEvent();
                            startEvent.setStrategyInstance(strategyInstance);
                            return startEvent;
                        case "SimpleStrategyStartFailedEvent":
                            SimpleStrategyStartFailedEvent startFailedEvent = new SimpleStrategyStartFailedEvent();
                            startFailedEvent.setErrorMessage(rpcEvent.getMessage());
                            startFailedEvent.setStrategyInstance(strategyInstance);
                            return startFailedEvent;
                        case "SimpleStrategyStoppedEvent":
                            SimpleStrategyStoppedEvent stopEvent = new SimpleStrategyStoppedEvent();
                            stopEvent.setStrategyInstance(strategyInstance);
                            return stopEvent;
                        case "SimpleStrategyUploadFailedEvent":
                            SimpleStrategyUploadFailedEvent failedEvent = new SimpleStrategyUploadFailedEvent();
                            failedEvent.setErrorMessage(rpcEvent.getMessage());
                            failedEvent.setStrategyInstance(strategyInstance);
                            return failedEvent;
                        case "SimpleStrategyUploadSucceededEvent":
                            SimpleStrategyUploadSucceededEvent succeededEvent = new SimpleStrategyUploadSucceededEvent();
                            succeededEvent.setStrategyInstance(strategyInstance);
                            return succeededEvent;
                        case "SimpleStrategyStatusChangedEvent":
                            SimpleStrategyStatusChangedEvent statusChangedEvent = new SimpleStrategyStatusChangedEvent();
                            statusChangedEvent.setStrategyInstance(strategyInstance);
                            getStrategyStatus(rpcEvent.getNewStatusValue()).ifPresent(strategyStatus -> statusChangedEvent.setNewValue(strategyStatus));
                            getStrategyStatus(rpcEvent.getOldStatusValue()).ifPresent(strategyStatus -> statusChangedEvent.setOldValue(strategyStatus));
                            return statusChangedEvent;
                        case "SimpleStrategyUnloadedEvent":
                            SimpleStrategyUnloadedEvent unloadedEvent = new SimpleStrategyUnloadedEvent();
                            unloadedEvent.setStrategyInstance(strategyInstance);
                            return unloadedEvent;
                        default:
                            throw new UnsupportedOperationException("Unexpected strategy event type: " + rpcEvent.getEventType());
                    }
                }
            }
        }
        return null;
    }
}
