package me.hyperperform.event.Calendar;

/**
 * Status of Google Calendar Invites
 *
 * @author  CodusMaximus
 * @version 1.0
 * @since   2016/07/06
 */

public enum AttendeeState
{
    /**
     * The attendee has accepted the invitation.
     */
    ACCEPTED,

    /**
     *  The attendee has tentatively accepted the invitation.
     */
    TENTATIVE,
    /**
     * The attendee has not responded to the invitation.
     */
    NEEDSACTION,
    /**
     * The attendee has declined the invitation.
     */
    DECLINED
}
