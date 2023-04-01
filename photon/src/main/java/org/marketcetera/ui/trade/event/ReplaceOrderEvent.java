package org.marketcetera.ui.trade.event;

import java.util.Properties;

import org.marketcetera.core.Pair;
import org.marketcetera.trade.ExecutionReport;
import org.marketcetera.trade.HasExecutionReport;
import org.marketcetera.ui.trade.view.orderticket.OrderTicketViewFactory;
import org.marketcetera.ui.view.ContentViewFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/* $License$ */

/**
 * Indicates that the given order is to be replaced.
 *
 * @author <a href="mailto:colin@marketcetera.com">Colin DuPlantis</a>
 * @version $Id$
 * @since $Release$
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ReplaceOrderEvent
        extends AbstractOrderTicketEvent
        implements HasExecutionReport
{
    /* (non-Javadoc)
     * @see org.marketcetera.trade.HasExecutionReport#getExecutionReport()
     */
    @Override
    public ExecutionReport getExecutionReport()
    {
        return executionReport;
    }
    /* (non-Javadoc)
     * @see org.marketcetera.web.events.NewWindowEvent#getWindowTitle()
     */
    @Override
    public String getWindowTitle()
    {
        return "Replace " + executionReport.getOrderID();
    }
    /* (non-Javadoc)
     * @see org.marketcetera.web.events.NewWindowEvent#getViewFactoryType()
     */
    @Override
    public Class<? extends ContentViewFactory> getViewFactoryType()
    {
        return OrderTicketViewFactory.class;
    }
    /* (non-Javadoc)
     * @see org.marketcetera.web.events.NewWindowEvent#getProperties()
     */
    @Override
    public Properties getProperties()
    {
        return windowProperties;
    }
    /* (non-Javadoc)
     * @see org.marketcetera.ui.events.NewWindowEvent#getWindowSize()
     */
    @Override
    public Pair<Double,Double> getWindowSize()
    {
        return Pair.create(850.0, 
                           200.0);
    }
    /**
     * Create a new ReplaceOrderEvent instance.
     *
     * @param inExecutionReport an <code>ExecutionReport</code> value
     * @param inProperties a <code>Properties</code> value
     */
    public ReplaceOrderEvent(ExecutionReport inExecutionReport,
                             Properties inProperties)
    {
        executionReport = inExecutionReport;
        windowProperties = inProperties;
    }
    /**
     * execution report value
     */
    private ExecutionReport executionReport;
    /**
     * properties with which to seed the window
     */
    private Properties windowProperties;
}
