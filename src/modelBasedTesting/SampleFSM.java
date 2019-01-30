package modelBasedTesting;

import nz.ac.waikato.modeljunit.*;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionCoverage;

/**
 *  Very first version of the FSM.
 *  Automaton focusing on the card authentication tries, abstracting the bills and money (no withdraw operation)
 */
public class SampleFSM implements FsmModel
{
    /** Variable representing the current state */
    private int state;
    int  i= 0;

    /** Variable representing the current state */
    private ATMAdapter adapter;

    /**
     *  Constructor. Initializes the data.
     */
    public SampleFSM()
    {
        state = 0;
        adapter = new ATMAdapter();
    }

    /**
     *  Inherited from the FsmModel interface.
     *  Provides a Comparable object that characterizes the current state.
     */
    public String getState()
    {
        return String.valueOf(state);
    }

    /**
     *  Inherited from the FsmModel interface.
     *  Provides a Comparable object that characterizes the current state.
     */
    public void reset(boolean testing)
    {
    	
        state = 0;
        adapter = new ATMAdapter();
    }

    /**
     *  Guard for the transition. Should be named after the transition name, suffixed by "Guard"
     */
    public boolean insertCardGuard() { return state == 0; }
    /**
     *  Transition itself. Annotated with @Action to indicate the method is a transition of the FSM.
     */
    @Action
    public void insertCard_Ok()
    {
	     if(adapter.insertCard_Ok() == 0 )
	     {
	    	 state = 1;
	    	 System.out.println("bonne carte inserée");
	     }
    }
  //  public boolean insertCard_KoGuard() { return state == 0; }
    /**
     *  Transition itself. Annotated with @Action to indicate the method is a transition of the FSM.
     */
  /* @Action
    public void insertCard_Ko()
    { System.out.println( adapter.insertCard_KO());
       int valeur = adapter.insertCard_KO();
        // evolution of the state
       if(valeur == -2 )
       {
    	   System.out.println("carte est bloqué");
    	   state = 4;
       }
       
     }
    */
    /**
     *  Guard for the transition. Should be named after the transition name, suffixed by "Guard"
     */
    public boolean inputPin_okGuard() {  return state == 1; }
    
    /**
     *  Transition itself. Annotated with @Action to indicate the method is a transition of the FSM.
     */
   
    @Action
    public void inputPin_ok()
    {  
    	if (adapter.inputPin() == 0)
    	{   
    		System.out.println("authentification reussi");
    		state = 3;	
    	}
    	if (adapter.inputPin() == -2)
    	{
    		System.out.println("authentification echoué");
    		state = 1;
    	}
    	if (adapter.inputPin() == -3 )
    	{
    		System.out.println("Carte bloqué"+i);
    		state = 4;
    	}
    
    } 
    //public boolean inputPin_KoGuard() { return state == 1; }
    
    /**
     *  Transition itself. Annotated with @Action to indicate the method is a transition of the FSM.
     */
   /* @Action
    public void inputPin_Ko()
    {System.out.println("ko ");
    	if (adapter.inputPin() == -2)
    	{
    		state = 1;
    		System.out.println("authentification echoué");
    	}
    }*/
 //   public boolean inputPin_BLockedGuard() { return state == 1; }
    
    /**
     *  Transition itself. Annotated with @Action to indicate the method is a transition of the FSM.
     */
   /* @Action
    public void inputPin_BLocked()
    {
    	if (adapter.inputPin() == -3)
    	{
    		state = 4;
    		System.out.println("Carte bloqué");
    	}
    } */
    public boolean takeCardGuard() { return state == 4; }
    
    /**
     *  Transition itself. Annotated with @Action to indicate the method is a transition of the FSM.
     */
    @Action
    public void takeCard()
    {
    	if (adapter.takeCard() != null)
    	{
    		state = 0;
    		System.out.println("Carte retirée");
    	}
    }
    public boolean chooseValidGuard() { i++; return state == 3; }
    
    /**
     *  Transition itself. Annotated with @Action to indicate the method is a transition of the FSM.
     */
    @Action
    public void chooseValid()
    {	
    	if (adapter.chooseValid() == 0 )
    	{
    		System.out.println("Montant valid");
    		state = 5;	
    	}
    	if (adapter.chooseValid() == -2 || adapter.chooseValid() == -3 )
    	{
    	    System.out.println("Montant not valid");
    	    state = 3;	
    	}
    	//else { System.out.println("Montant valid");}
    }
   // public boolean chooseNotPossibleValueGuard() { return state == 3; }
    
    /**
     *  Transition itself. Annotated with @Action to indicate the method is a transition of the FSM.
     */
   /* @Action
    public void chooseNotPossibleValue()
    {
    	if (adapter.chooseNotPossibleValue() == -2)
    	{
    		state = 3;
    		System.out.println("Montant not possible ");
    	}
    	else { System.out.println("Montant valid");}
    }
    public boolean chooseMoreBalenceGuard() { return state == 3; }
    
    /**
     *  Transition itself. Annotated with @Action to indicate the method is a transition of the FSM.
     */
  /*  @Action
    public void chooseMoreBalence()
    {
    	if (adapter.chooseMoreBalence() == -3)
    	{
    		state = 3;
    		System.out.println("Montant insuffisant dans votre compte ");
    	}
    	else { System.out.println("Montant valid");
    	       state = 5;
    	       }
    }
    */
    public boolean debitAccountGuard() { return state == 5; }
    
    /**
     *  Transition itself. Annotated with @Action to indicate the method is a transition of the FSM.
     */
    @Action
    public void debitAccount()
    {
    	if (adapter.debitAccount() == true)
    	{
    		System.out.println("Transaction validated");
    		state = 6;
    	}
    	else {
    		System.out.println("Transaction not validated");
    		state = 0;
    	}
    }
    
    public boolean takeCardBeforeBillsGuard() { return state == 6; }
    
    /**
     *  Transition itself. Annotated with @Action to indicate the method is a transition of the FSM.
     */
    @Action
    public void takeCardBeforeBills()
    {
    	if (adapter.takeCard() != null)
    	{
    		System.out.println("Carte retirée");
    		state = 0;
    	}
    }
    
    /***
     * Main program
     */
    public static void main(String[] argv) {

        // initialization of the model
        SampleFSM model = new SampleFSM();

        /**
         * Test a system by making random walks through an EFSM model of the system.
         */
     //  Tester tester = new RandomTester(model);

        /**
         * Test a system by making greedy walks through an EFSM model of the system.
         * A greedy random walk gives preference to transitions that have never been taken before.
         * Once all transitions out of a state have been taken, it behaves the same as a random walk.
         */
        Tester tester = new GreedyTester(model);

        /**
         * Creates a GreedyTester that will terminate each test sequence after getLoopTolerance() visits to a state.
         */
        // AllRoundTester tester = new AllRoundTester(model);
        // tester.setLoopTolerance(3);

        /**
         * A test generator that looks N-levels ahead in the graph. It chooses the highest-valued
         * transition (action) that is enabled in the current state.
         * DEPTH = How far should we look ahead?
         * NEW_ACTION = How worthwhile is it to use a completely new action?
         * NEW_TRANS = How worthwhile is it to explore a new transition?
         */
       /* LookaheadTester tester = new LookaheadTester(model);
        tester.setDepth(10);
        tester.setNewActionValue(50);
        tester.setNewTransValue(100);  // priority on new transitions w.r.t. new actions
*/
        // computes the graph to get coverage measure information
        tester.buildGraph();

        // usual paramaterization
        tester.addListener(new VerboseListener());
        tester.addListener(new StopOnFailureListener());
        tester.addCoverageMetric(new TransitionCoverage());
        tester.addCoverageMetric(new StateCoverage() {
            @Override
            public String getName() {
                return "Total state coverage";
            }
        });
        tester.addCoverageMetric(new ActionCoverage());

        // run the test generation (10 steps)  <-- CAN BE INCREASED TO PRODUCE MORE TESTS!
        tester.generate(10);

        // prints the coverage and quits the execution
        tester.printCoverage();
    }
}