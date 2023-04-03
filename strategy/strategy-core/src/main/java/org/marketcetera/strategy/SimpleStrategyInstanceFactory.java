//
// this file is automatically generated
//
package org.marketcetera.strategy;

/* $License$ */

/**
 * Creates new {@link SimpleStrategyInstance} objects.
 *
 * @author <a href="mailto:colin@marketcetera.com">Colin DuPlantis</a>
 * @version $Id$
 * @since $Release$
 */
public class SimpleStrategyInstanceFactory
        implements org.marketcetera.strategy.StrategyInstanceFactory
{
    /**
     * Create a new <code>org.marketcetera.strategy.SimpleStrategyInstance</code> instance.
     *
     * @return a <code>org.marketcetera.strategy.SimpleStrategyInstance</code> value
     */
    @Override
    public org.marketcetera.strategy.SimpleStrategyInstance create()
    {
        return new org.marketcetera.strategy.SimpleStrategyInstance();
    }
    /**
     * Create a new <code>org.marketcetera.strategy.SimpleStrategyInstance</code> instance from the given object.
     *
     * @param inStrategyInstance an <code>org.marketcetera.strategy.SimpleStrategyInstance</code> value
     * @return an <code>org.marketcetera.strategy.SimpleStrategyInstance</code> value
     */
    @Override
    public org.marketcetera.strategy.SimpleStrategyInstance create(org.marketcetera.strategy.StrategyInstance inSimpleStrategyInstance)
    {
        return new org.marketcetera.strategy.SimpleStrategyInstance(inSimpleStrategyInstance);
    }
}