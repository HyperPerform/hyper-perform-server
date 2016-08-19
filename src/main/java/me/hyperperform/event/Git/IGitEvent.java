package me.hyperperform.event.Git;

import me.hyperperform.event.IEvent;

import java.sql.Timestamp;

/**
 * Git Interface for all git related events
 *
 * @author  CodusMaximus
 * @version 1.0
 * @since   2016/07/05
 */

public interface IGitEvent extends IEvent
{
	/**
	 *
	 * @param timestamp initialize the date and time the event was triggered
     */
	public void setDate(Timestamp timestamp);

	/**
	 *
	 * @param name initialize the repository name for the event
     */
	public void setRepoName(String name);

	/**
	 *
	 * @param user initialize the username of who triggered an event
     */
	public void setUser(String user);
}