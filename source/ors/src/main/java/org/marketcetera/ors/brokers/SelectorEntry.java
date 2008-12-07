package org.marketcetera.ors.brokers;

import org.marketcetera.trade.DestinationID;
import org.marketcetera.trade.SecurityType;
import org.marketcetera.util.except.I18NRuntimeException;
import org.marketcetera.util.log.I18NBoundMessage1P;
import org.marketcetera.util.misc.ClassVersion;

/**
 * The in-memory representation of a selector entry.
 *
 * @author tlerios@marketcetera.com
 * @since $Release$
 * @version $Id$
 */

/* $License$ */

@ClassVersion("$Id$") //$NON-NLS-1$
public class SelectorEntry
{

    // INSTANCE DATA.

    private final SpringSelectorEntry mSpringSelectorEntry;
    private final SecurityType mTargetType;
    private final DestinationID mDestinationID;


    // CONSTRUCTORS.

    /**
     * Creates a new entry based on the given configuration.
     *
     * @param springSelectorEntry The configuration.
     */

    public SelectorEntry
        (SpringSelectorEntry springSelectorEntry)
    {
        mSpringSelectorEntry=springSelectorEntry;
        mTargetType=SecurityType.getInstanceForFIXValue
            (getSpringSelectorEntry().getTargetType());
        if (SecurityType.Unknown.equals(getTargetType())) {
            throw new I18NRuntimeException
                (new I18NBoundMessage1P
                 (Messages.UNKNOWN_SECURITY_TYPE,
                  getSpringSelectorEntry().getTargetType()));
                                           
        }
        mDestinationID=new DestinationID
            (getSpringSelectorEntry().getDestination().getId());
    }


    // INSTANCE METHODS.

    /**
     * Returns the receiver's configuration.
     *
     * @return The configuration.
     */

    public SpringSelectorEntry getSpringSelectorEntry()
    {
        return mSpringSelectorEntry;
    }

    /**
     * Returns the receiver's target type.
     *
     * @return The target type, which will guaranteed to be known.
     */

    public SecurityType getTargetType()
    {
        return mTargetType;
    }

    /**
     * Returns the receiver's destination ID.
     *
     * @return The ID.
     */

    public DestinationID getDestination()
    {
        return mDestinationID;
    }

    /**
     * Returns the receiver's skip-if-unavailable flag.
     *
     * @return The flag.
     */

    public boolean getSkipIfUnavailable()
    {
        return getSpringSelectorEntry().getSkipIfUnavailable();
    }
}