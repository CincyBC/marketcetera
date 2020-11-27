//
// this file is automatically generated
//
package org.marketcetera.trade.pnl.event;

/* $License$ */

/**
 * Indicates that the position of an instrument has changed for a user.
 *
 * @author <a href="mailto:colin@marketcetera.com">Colin DuPlantis</a>
 * @version $Id$
 * @since $Release$
 */
public interface PositionChangedEvent
{
    /**
     * Get the position value.
     *
     * @return a <code>org.marketcetera.trade.pnl.Position</code> value
     */
    org.marketcetera.trade.pnl.Position getPosition();
}