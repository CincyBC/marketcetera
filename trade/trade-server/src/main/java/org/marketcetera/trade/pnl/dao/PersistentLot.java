//
// this file is automatically generated
//
package org.marketcetera.trade.pnl.dao;

/* $License$ */

/**
 * Identifies a lot used to calculate profit and loss.
 *
 * @author <a href="mailto:colin@marketcetera.com">Colin DuPlantis</a>
 * @version $Id$
 * @since $Release$
 */
@javax.persistence.Entity(name="Lot")
@javax.persistence.Table(name="pnl_lots")
public class PersistentLot
        extends org.marketcetera.persist.EntityBase
        implements org.marketcetera.trade.pnl.Lot
{
    /**
     * Create a new PersistentLot instance.
     */
    public PersistentLot() {}
    /**
     * Create a new PersistentLot instance.
     *
     * @param inLot a <code>Lot</code> value
     */
    public PersistentLot(org.marketcetera.trade.pnl.Lot inLot)
    {
        setUser(inLot.getUser());
        setTrade(inLot.getTrade());
        setPosition(inLot.getPosition());
        setQuantity(inLot.getQuantity());
        setAllocatedQuantity(inLot.getAllocatedQuantity());
        setEffectiveDate(inLot.getEffectiveDate());
        setBasisPrice(inLot.getBasisPrice());
        setGain(inLot.getGain());
        setTradePrice(inLot.getTradePrice());
    }
    /**
     * Get the user value.
     *
     * @return a <code>org.marketcetera.admin.User</code> value
     */
    @Override
    public org.marketcetera.admin.User getUser()
    {
        return user;
    }
    /**
     * Set the user value.
     *
     * @param inUser a <code>org.marketcetera.admin.User</code> value
     */
    @Override
    public void setUser(org.marketcetera.admin.User inUser)
    {
        user = (org.marketcetera.admin.user.PersistentUser)inUser;
    }
    /**
     * Get the trade value.
     *
     * @return a <code>Trade</code> value
     */
    @Override
    public org.marketcetera.trade.pnl.Trade getTrade()
    {
        return trade;
    }
    /**
     * Set the trade value.
     *
     * @param inTrade a <code>Trade</code> value
     */
    @Override
    public void setTrade(org.marketcetera.trade.pnl.Trade inTrade)
    {
        trade = (PersistentTrade)inTrade;
    }
    /**
     * Get the position value.
     *
     * @return a <code>Position</code> value
     */
    @Override
    public org.marketcetera.trade.pnl.Position getPosition()
    {
        return position;
    }
    /**
     * Set the position value.
     *
     * @param inPosition a <code>Position</code> value
     */
    @Override
    public void setPosition(org.marketcetera.trade.pnl.Position inPosition)
    {
        position = (PersistentPosition)inPosition;
    }
    /**
     * Get the quantity value.
     *
     * @return a <code>java.math.BigDecimal</code> value
     */
    @Override
    public java.math.BigDecimal getQuantity()
    {
        return quantity;
    }
    /**
     * Set the quantity value.
     *
     * @param inQuantity a <code>java.math.BigDecimal</code> value
     */
    @Override
    public void setQuantity(java.math.BigDecimal inQuantity)
    {
        quantity = inQuantity == null ? java.math.BigDecimal.ZERO : inQuantity;
    }
    /**
     * Get the allocatedQuantity value.
     *
     * @return a <code>java.math.BigDecimal</code> value
     */
    @Override
    public java.math.BigDecimal getAllocatedQuantity()
    {
        return allocatedQuantity;
    }
    /**
     * Set the allocatedQuantity value.
     *
     * @param inAllocatedQuantity a <code>java.math.BigDecimal</code> value
     */
    @Override
    public void setAllocatedQuantity(java.math.BigDecimal inAllocatedQuantity)
    {
        allocatedQuantity = inAllocatedQuantity == null ? java.math.BigDecimal.ZERO : inAllocatedQuantity;
    }
    /**
     * Get the effectiveDate value.
     *
     * @return a <code>java.util.Date</code> value
     */
    @Override
    public java.util.Date getEffectiveDate()
    {
        return effectiveDate;
    }
    /**
     * Set the effectiveDate value.
     *
     * @param inEffectiveDate a <code>java.util.Date</code> value
     */
    @Override
    public void setEffectiveDate(java.util.Date inEffectiveDate)
    {
        effectiveDate = inEffectiveDate;
    }
    /**
     * Get the basisPrice value.
     *
     * @return a <code>java.math.BigDecimal</code> value
     */
    @Override
    public java.math.BigDecimal getBasisPrice()
    {
        return basisPrice;
    }
    /**
     * Set the basisPrice value.
     *
     * @param inBasisPrice a <code>java.math.BigDecimal</code> value
     */
    @Override
    public void setBasisPrice(java.math.BigDecimal inBasisPrice)
    {
        basisPrice = inBasisPrice == null ? java.math.BigDecimal.ZERO : inBasisPrice;
    }
    /**
     * Get the gain value.
     *
     * @return a <code>java.math.BigDecimal</code> value
     */
    @Override
    public java.math.BigDecimal getGain()
    {
        return gain;
    }
    /**
     * Set the gain value.
     *
     * @param inGain a <code>java.math.BigDecimal</code> value
     */
    @Override
    public void setGain(java.math.BigDecimal inGain)
    {
        gain = inGain == null ? java.math.BigDecimal.ZERO : inGain;
    }
    /**
     * Get the tradePrice value.
     *
     * @return a <code>java.math.BigDecimal</code> value
     */
    @Override
    public java.math.BigDecimal getTradePrice()
    {
        return tradePrice;
    }
    /**
     * Set the tradePrice value.
     *
     * @param inTradePrice a <code>java.math.BigDecimal</code> value
     */
    @Override
    public void setTradePrice(java.math.BigDecimal inTradePrice)
    {
        tradePrice = inTradePrice == null ? java.math.BigDecimal.ZERO : inTradePrice;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Lot [")
            .append("user=").append(user)
            .append(", trade=").append(trade)
            .append(", position=").append(position)
            .append(", quantity=").append(org.marketcetera.core.BigDecimalUtil.render(quantity))
            .append(", allocatedQuantity=").append(org.marketcetera.core.BigDecimalUtil.render(allocatedQuantity))
            .append(", effectiveDate=").append(effectiveDate)
            .append(", basisPrice=").append(org.marketcetera.core.BigDecimalUtil.renderCurrency(basisPrice))
            .append(", gain=").append(org.marketcetera.core.BigDecimalUtil.renderCurrency(gain))
            .append(", tradePrice=").append(org.marketcetera.core.BigDecimalUtil.renderCurrency(tradePrice)).append("]");
        return builder.toString();
    }
    /**
     * user which owns lot
     */
    @javax.persistence.ManyToOne(fetch=javax.persistence.FetchType.EAGER,optional=false)
    @javax.persistence.JoinColumn(name="user_id",nullable=false)
    private org.marketcetera.admin.user.PersistentUser user;
    /**
     * trade which consumes lot, may be <code>null</code>
     */
    @javax.persistence.ManyToOne(fetch=javax.persistence.FetchType.EAGER,optional=false)
    @javax.persistence.JoinColumn(name="trade_id",nullable=false)
    private PersistentTrade trade;
    /**
     * position to which this lot contributes
     */
    @javax.persistence.ManyToOne(fetch=javax.persistence.FetchType.EAGER,optional=false)
    @javax.persistence.JoinColumn(name="position_id",nullable=false)
    private PersistentPosition position;
    /**
     * size of lot
     */
    @javax.persistence.Column(name="quantity",precision=org.marketcetera.core.PlatformServices.DECIMAL_PRECISION,scale=org.marketcetera.core.PlatformServices.DECIMAL_SCALE,nullable=false)
    private java.math.BigDecimal quantity = java.math.BigDecimal.ZERO;
    /**
     * quantity of this lot that has been allocated to account for a sell trade
     */
    @javax.persistence.Column(name="allocated_quantity",precision=org.marketcetera.core.PlatformServices.DECIMAL_PRECISION,scale=org.marketcetera.core.PlatformServices.DECIMAL_SCALE,nullable=false)
    private java.math.BigDecimal allocatedQuantity = java.math.BigDecimal.ZERO;
    /**
     * date lot was created
     */
    @javax.persistence.Column(name="effective_date",nullable=false)
    private java.util.Date effectiveDate;
    /**
     * basis price value
     */
    @javax.persistence.Column(name="basis_price",precision=org.marketcetera.core.PlatformServices.DECIMAL_PRECISION,scale=org.marketcetera.core.PlatformServices.DECIMAL_SCALE,nullable=false)
    private java.math.BigDecimal basisPrice = java.math.BigDecimal.ZERO;
    /**
     * realized gain from this lot
     */
    @javax.persistence.Column(name="gain",precision=org.marketcetera.core.PlatformServices.DECIMAL_PRECISION,scale=org.marketcetera.core.PlatformServices.DECIMAL_SCALE,nullable=false)
    private java.math.BigDecimal gain = java.math.BigDecimal.ZERO;
    /**
     * trade price value of this lot
     */
    @javax.persistence.Column(name="trade_price",precision=org.marketcetera.core.PlatformServices.DECIMAL_PRECISION,scale=org.marketcetera.core.PlatformServices.DECIMAL_SCALE,nullable=false)
    private java.math.BigDecimal tradePrice = java.math.BigDecimal.ZERO;
    private static final long serialVersionUID = 682666976L;
}