//
// this file is automatically generated
//
package org.marketcetera.strategy;

/* $License$ */

/**
 * Provides an RPC RpcClientFactory for StrategyRpc services.
 *
 * @author <a href="mailto:colin@marketcetera.com">Colin DuPlantis</a>
 * @version $Id$
 * @since $Release$
 */
public class StrategyRpcClientFactory
        implements StrategyClientFactory<StrategyRpcClientParameters>
{
    /* (non-Javadoc)
     * @see org.marketcetera.strategy.StrategyClientFactory#create(java.lang.Object)
     */
    @Override
    public org.marketcetera.strategy.StrategyClient create(org.marketcetera.strategy.StrategyRpcClientParameters inParameterClazz)
    {
        return applicationContext.getBean(org.marketcetera.strategy.StrategyClient.class,inParameterClazz);
    }
    /**
     * provides access to the application context
     */
    @org.springframework.beans.factory.annotation.Autowired
    private org.springframework.context.ApplicationContext applicationContext;
}