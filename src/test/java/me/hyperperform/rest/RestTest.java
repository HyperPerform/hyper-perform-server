package me.hyperperform.rest;

import me.hyperperform.listener.GitListener;
import me.hyperperform.listener.CalendarListener;
import me.hyperperform.event.MockEvent;

import me.hyperperform.listener.TravisListener;
import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.plugins.server.resourcefactory.POJOResourceFactory;
import org.jboss.resteasy.mock.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.core.MediaType;

/**
 * Class containing JUnit tests for testing the REST component.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = RestTestConfig.class)
public class RestTest
{
	/**
	 * Test for checking whether or not the GitEvents are being accepted and persisted. A mock git push event is used
	 * to carry out the test.
	 * @throws Exception if there was an error in processing the data
     */
	@Test
	public void gitEventTest() throws Exception {

		System.out.println("Running git listener Push test..");
		POJOResourceFactory noDef = new POJOResourceFactory(GitListener.class);
		Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();

		dispatcher.getRegistry().addResourceFactory(noDef);

		MockHttpRequest request = MockHttpRequest.post("/gitEvent");

		request.header("X-GitHub-Event", "push");
		request.contentType(MediaType.APPLICATION_JSON_TYPE);

		request.content(MockEvent.gitPushEvent.getBytes());

		MockHttpResponse response = new MockHttpResponse();
		dispatcher.invoke(request, response);

		Assert.assertEquals(response.getStatus(), 200);
	}

	@Test
	public void gitIssueEventTest() throws Exception
	{
		System.out.println("Running git listener Issue test..");

		POJOResourceFactory noDef = new POJOResourceFactory(GitListener.class);
		Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();

		dispatcher.getRegistry().addResourceFactory(noDef);

		MockHttpRequest request = MockHttpRequest.post("/gitEvent");

		request.header("X-GitHub-Event", "issues");
		request.contentType(MediaType.APPLICATION_JSON_TYPE);

		request.content(MockEvent.gitIssuesEvent.getBytes());

		MockHttpResponse response = new MockHttpResponse();
		dispatcher.invoke(request, response);

		Assert.assertEquals(response.getStatus(), 200);
	}

	/**
	 * Simple rest test for calendar
	 * @throws Exception if there was an error in processing the data
     */
	@Test
	public void calendarSimpleTest() throws Exception
	{
		System.out.println("Running calendar listener test..");

		POJOResourceFactory noDef = new POJOResourceFactory(CalendarListener.class);
		Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();

		dispatcher.getRegistry().addResourceFactory(noDef);

		MockHttpRequest request = MockHttpRequest.get("/calendar");

		request.header("X-Goog-Resource-URI", "https://www.googleapis.com/calendar/v3/calendars/hk3487gd7h57oh9g71he9tfjs0@group.calendar.google.com/events");
		request.contentType(MediaType.APPLICATION_JSON_TYPE);

		request.content(MockEvent.calendarEvents.getBytes());

		MockHttpResponse response = new MockHttpResponse();
		dispatcher.invoke(request, response);

		Assert.assertEquals(response.getStatus(), 200);
	}


	/**
	 * Test to determine whether or not error codes are received if an invalid URL is accessed.
	 * @throws Exception if there was an error in processing the data
     */
	@Test
	public void invalidLinkTest() throws Exception
	{
		System.out.println("Running listener invalid link test..");

		POJOResourceFactory noDef = new POJOResourceFactory(GitListener.class);
		Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();

		dispatcher.getRegistry().addResourceFactory(noDef);

		MockHttpRequest request = MockHttpRequest.get("/gitEvent/random");
		MockHttpResponse response = new MockHttpResponse();
		dispatcher.invoke(request, response);

		Assert.assertEquals(response.getStatus(), 404);
	}

	/**
	 * Test to check whether or not listener can handle different timezones
	 * @throws Exception if there was an error in processing the data
     */
	@Test
	public void timezoneTest() throws Exception
	{
		System.out.println("Running listener timezone test..");

		POJOResourceFactory noDef = new POJOResourceFactory(GitListener.class);
		Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();

		dispatcher.getRegistry().addResourceFactory(noDef);

		MockHttpRequest request = MockHttpRequest.post("/gitEvent");

		request.header("X-GitHub-Event", "push");
		request.contentType(MediaType.APPLICATION_JSON_TYPE);

		request.content(MockEvent.alternativeGitPush.getBytes());

		MockHttpResponse response = new MockHttpResponse();
		dispatcher.invoke(request, response);

		Assert.assertEquals(response.getStatus(), 200);
	}

	/**
	 * Test for travis web hooks
	 * @throws Exception if there was an error in processing the data
     */
	@Test
	public void travisTest() throws Exception
	{
		System.out.println("Running travis listener test..");

		POJOResourceFactory noDef = new POJOResourceFactory(TravisListener.class);
		Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();

		dispatcher.getRegistry().addResourceFactory(noDef);

		MockHttpRequest request = MockHttpRequest.post("/TravisEvent");
		request.addFormHeader("payload", MockEvent.travisEvent);

		request.contentType(MediaType.APPLICATION_FORM_URLENCODED_TYPE);

		MockHttpResponse response = new MockHttpResponse();
		dispatcher.invoke(request, response);

		Assert.assertEquals(response.getStatus(), 200);
	}

	@Test
	public void loginTest() throws Exception
	{
		System.out.println("Running login listener test..");

		POJOResourceFactory noDef = new POJOResourceFactory(LoginRest.class);
		Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();

		dispatcher.getRegistry().addResourceFactory(noDef);

		MockHttpRequest request = MockHttpRequest.post("/verifyDetails");
		request.addFormHeader("payload", MockEvent.loginEvent);

		request.contentType(MediaType.APPLICATION_FORM_URLENCODED_TYPE);

		MockHttpResponse response = new MockHttpResponse();
		dispatcher.invoke(request, response);

		Assert.assertEquals(response.getStatus(), 200);
	}
}