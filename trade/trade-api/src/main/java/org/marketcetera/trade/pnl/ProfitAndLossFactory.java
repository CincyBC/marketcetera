//
// this file is automatically generated
//
package org.marketcetera.trade.pnl;

/* $License$ */

/**
 * Creates new {@link ProfitAndLoss} objects.
 *
 * @author <a href="mailto:colin@marketcetera.com">Colin DuPlantis</a>
 * @version $Id$
 * @since $Release$
 */
public interface ProfitAndLossFactory
        extends org.marketcetera.core.Factory<ProfitAndLoss>
{
    /**
     * Create a new <code>ProfitAndLoss</code> instance.
     *
     * @return a <code>ProfitAndLoss</code> value
     */
    @Override
    ProfitAndLoss create();
    /**
     * Create a new <code>ProfitAndLoss</code> instance from the given object.
     *
     * @param inObject a <code>ProfitAndLoss</code> value
     * @return a <code>ProfitAndLoss</code> value
     */
    @Override
    ProfitAndLoss create(ProfitAndLoss inProfitAndLoss);
}