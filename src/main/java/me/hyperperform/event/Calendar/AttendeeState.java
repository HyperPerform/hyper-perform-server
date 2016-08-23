package me.hyperperform.event.Calendar;

/**
 * hyper-perform
 * Group: CodusMaximus
 * Date: 2016/07/25
 * Feature: Attendee State
 */

/**
 * Status of Google Calendar Invites
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
