//
// this file is automatically generated
//
package org.marketcetera.strategy.dao;

/* $License$ */

/**
 * Identifies a unique strategy instance.
 *
 * @author <a href="mailto:colin@marketcetera.com">Colin DuPlantis</a>
 * @version $Id$
 * @since $Release$
 */
@javax.persistence.Entity(name="StrategyInstance")
@javax.persistence.Table(name="metc_strategy_instances")
public class PersistentStrategyInstance
        extends org.marketcetera.persist.EntityBase
        implements org.marketcetera.strategy.StrategyInstance,org.marketcetera.admin.HasUser
{
    /**
     * Create a new PersistentStrategyInstance instance.
     */
    public PersistentStrategyInstance() {}
    /**
     * Create a new PersistentStrategyInstance instance.
     *
     * @param inStrategyInstance a <code>StrategyInstance</code> value
     */
    public PersistentStrategyInstance(org.marketcetera.strategy.StrategyInstance inStrategyInstance)
    {
        setUser(inStrategyInstance.getUser());
        setName(inStrategyInstance.getName());
        setFilename(inStrategyInstance.getFilename());
        setHash(inStrategyInstance.getHash());
        setStarted(inStrategyInstance.getStarted());
        setStatus(inStrategyInstance.getStatus());
    }
    /**
     * Get the user value.
     *
     * @return an <code>org.marketcetera.admin.User</code> value
     */
    @Override
    public org.marketcetera.admin.User getUser()
    {
        return user;
    }
    /**
     * Set the user value.
     *
     * @param inUser an <code>org.marketcetera.admin.User</code> value
     */
    @Override
    public void setUser(org.marketcetera.admin.User inUser)
    {
        user = (org.marketcetera.admin.user.PersistentUser)inUser;
    }
    /**
     * Get the name value.
     *
     * @return a <code>String</code> value
     */
    @Override
    public String getName()
    {
        return name;
    }
    /**
     * Set the name value.
     *
     * @param inName a <code>String</code> value
     */
    @Override
    public void setName(String inName)
    {
        name = org.apache.commons.lang.StringUtils.trimToNull(inName);
    }
    /**
     * Get the filename value.
     *
     * @return a <code>String</code> value
     */
    @Override
    public String getFilename()
    {
        return filename;
    }
    /**
     * Set the filename value.
     *
     * @param inFilename a <code>String</code> value
     */
    @Override
    public void setFilename(String inFilename)
    {
        filename = org.apache.commons.lang.StringUtils.trimToNull(inFilename);
    }
    /**
     * Get the hash value.
     *
     * @return a <code>String</code> value
     */
    @Override
    public String getHash()
    {
        return hash;
    }
    /**
     * Set the hash value.
     *
     * @param inHash a <code>String</code> value
     */
    @Override
    public void setHash(String inHash)
    {
        hash = org.apache.commons.lang.StringUtils.trimToNull(inHash);
    }
    /**
     * Get the started value.
     *
     * @return a <code>java.util.Date</code> value
     */
    @Override
    public java.util.Date getStarted()
    {
        return started;
    }
    /**
     * Set the started value.
     *
     * @param inStarted a <code>java.util.Date</code> value
     */
    @Override
    public void setStarted(java.util.Date inStarted)
    {
        started = inStarted;
    }
    /**
     * Get the status value.
     *
     * @return an <code>org.marketcetera.strategy.StrategyStatus</code> value
     */
    @Override
    public org.marketcetera.strategy.StrategyStatus getStatus()
    {
        return status;
    }
    /**
     * Set the status value.
     *
     * @param inStatus an <code>org.marketcetera.strategy.StrategyStatus</code> value
     */
    @Override
    public void setStatus(org.marketcetera.strategy.StrategyStatus inStatus)
    {
        status = inStatus;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("StrategyInstance [")
            .append("user=").append(user)
            .append(", name=").append(name)
            .append(", filename=").append(filename)
            .append(", hash=").append(hash)
            .append(", started=").append(started)
            .append(", status=").append(status).append("]");
        return builder.toString();
    }
    /**
     * user which owns this strategy
     */
    @javax.persistence.ManyToOne
    @javax.persistence.JoinColumn(name="user_id",nullable=true)
    private org.marketcetera.admin.user.PersistentUser user;
    /**
     * uniquely describes the strategy
     */
    @javax.persistence.Column(name="name",nullable=true,unique=false)
    private String name;
    /**
     * indicates the server-local file which contains the strategy
     */
    @javax.persistence.Column(name="filename",nullable=true,unique=false)
    private String filename;
    /**
     * indicates the strategy hash value
     */
    @javax.persistence.Column(name="hash",nullable=true,unique=false)
    private String hash;
    /**
     * date strategy was started
     */
    @javax.persistence.Column(name="started",nullable=true,unique=false)
    private java.util.Date started;
    /**
     * describes the state of a loaded strategy
     */
    @javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
    @javax.persistence.Column(name="status",nullable=true,unique=false)
    private org.marketcetera.strategy.StrategyStatus status;
    private static final long serialVersionUID = -855697822L;
}
