package grails_akka

import static org.junit.Assert.*
import org.junit.*
import org.junit.rules.*

import akka.actor.*


/**
 * LocalAkkaIntegrationTests
 * <br/>
 * Integration tests with a Local Akka System.
 */
class LocalAkkaIntegrationTests 
{

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test(expected = InstantiationException.class)
	void testClassAvailable_UntypedActor() 
	{
        log.info "testClassAvailable_UntypedActor()"

		def className = null;
		def classInstance = null;

		className = "akka.actor.UntypedActor"  // abstract, so not instantiable ...
		classInstance = Class.forName(className).newInstance();
		println("$className instance is: $classInstance")
		// assertNotNull classInstance
		// here I expect an InstantiationException thrown
	}

    @Test(expected = InstantiationException.class)
	void testClassAvailable_ActorSystem() 
	{
        log.info "testClassAvailable_ActorSystem()"

		def className = null;
		def classInstance = null;

		className = "akka.actor.ActorSystem"  // abstract, so not instantiable ...
		classInstance = Class.forName(className).newInstance();
		println("$className instance is: $classInstance")
		// assertNotNull classInstance
		// here I expect an InstantiationException thrown
	}

    @Test
	void testLocalAkkaSystem_Greetings() 
	{
        log.info "testLocalAkkaSystem_Greetings()"

		// creates the local actor system
		ActorSystem system = ActorSystem.create("GreetingSystem")
		println("Actor System instance is: $system")
		assertNotNull system

		// get a reference to our greeting actor
		ActorRef greetingActor = system.actorOf(new Props(GreetingActor.class), "greeting_actor")
		println("Actor Reference instance is: $greetingActor")
		assertNotNull greetingActor

		// send to the greeting actor a null message
		greetingActor.tell(null);
		assertNotNull greetingActor  // dummy

		// send to the greeting actor a Greeting message
		greetingActor.tell(new Greeting("Test Greeting"));
		assertNotNull greetingActor  // dummy

		// send to the greeting actor an unknown message
		greetingActor.tell(new String("Test String"));
		assertNotNull greetingActor  // dummy
	}

}