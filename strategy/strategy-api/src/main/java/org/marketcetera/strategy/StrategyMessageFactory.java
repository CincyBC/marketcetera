//
// this file is automatically generated
//
package org.marketcetera.strategy;

/* $License$ */

/**
 * Creates new {@link StrategyMessage} objects.
 *
 * @author <a href="mailto:colin@marketcetera.com">Colin DuPlantis</a>
 * @version $Id$
 * @since $Release$
 */
public interface StrategyMessageFactory
        extends org.marketcetera.core.Factory<StrategyMessage>
{
    /**
     * Create a new <code>StrategyMessage</code> instance.
     *
     * @return a <code>StrategyMessage</code> value
     */
    @Override
    StrategyMessage create();
    /**
     * Create a new <code>StrategyMessage</code> instance from the given object.
     *
     * @param inStrategyMessage a <code>StrategyMessage</code> value
     * @return a <code>StrategyMessage</code> value
     */
    @Override
    StrategyMessage create(StrategyMessage inStrategyMessage);
}