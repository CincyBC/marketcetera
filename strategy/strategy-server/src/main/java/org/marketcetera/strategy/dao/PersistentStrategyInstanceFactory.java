//
// this file is automatically generated
//
package org.marketcetera.strategy.dao;

import org.marketcetera.core.Preserve;

/* $License$ */

/**
 * Creates new {@link PersistentStrategyInstance} objects.
 *
 * @author <a href="mailto:colin@marketcetera.com">Colin DuPlantis</a>
 * @version $Id$
 * @since $Release$
 */
@Preserve
public class PersistentStrategyInstanceFactory
        implements org.marketcetera.strategy.StrategyInstanceFactory
{
    /**
     * Create a new <code>org.marketcetera.strategy.dao.PersistentStrategyInstance</code> instance.
     *
     * @return a <code>org.marketcetera.strategy.dao.PersistentStrategyInstance</code> value
     */
    @Override
    public org.marketcetera.strategy.dao.PersistentStrategyInstance create()
    {
        return new org.marketcetera.strategy.dao.PersistentStrategyInstance();
    }
    /**
     * Create a new <code>org.marketcetera.strategy.dao.PersistentStrategyInstance</code> instance from the given object.
     *
     * @param inStrategyInstance an <code>org.marketcetera.strategy.dao.PersistentStrategyInstance</code> value
     * @return an <code>org.marketcetera.strategy.dao.PersistentStrategyInstance</code> value
     */
    @Override
    public org.marketcetera.strategy.dao.PersistentStrategyInstance create(org.marketcetera.strategy.StrategyInstance inStrategyInstance)
    {
        return new org.marketcetera.strategy.dao.PersistentStrategyInstance(inStrategyInstance);
    }
}
