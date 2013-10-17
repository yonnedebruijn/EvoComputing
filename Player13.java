import org.vu.contest.ContestSubmission;
import org.vu.contest.ContestEvaluation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Properties;


public class Player13 implements ContestSubmission{
    Random rand;

    final private ArrayList<Individual> population;
    final private ArrayList<Individual> parent_population;
    final private ArrayList<Individual> child_population;

    ContestEvaluation evaluation;
    final Parameters p = new Parameters();

    private int eval_limit;     // amount of allowed tries on the server
    private int counter = 0;    // counter that represents the amount of evaluated tries
    final private int parent_size = (int) (p.parent_portion * p.population_size);
    final private int elite_size = (int) (p.elite_portion * p.population_size);

    public Player13 ()
    {
        rand = new Random();
        //Create the populations of 'individuals'
        population = new ArrayList<Individual>(p.population_size);
        parent_population = new ArrayList<Individual>(parent_size);
        child_population = new ArrayList<Individual>(2*parent_size);

        for(int i = 0; i < p.population_size; i++)
        {
            population.add(new Individual(rand,p));
        }

    }


    public void setSeed(long seed)
    {
        // Set seed of algortihms random process
        rand.setSeed(seed);
    }

    public void setProperties(Properties props)     // writes the evaluation problems' properties to the parameters-class
    {
        eval_limit = Integer.parseInt(props.getProperty("Evaluations"));
        p.isMultimodal = Boolean.parseBoolean(props.getProperty("Multimodal"));
        p.hasStructure = Boolean.parseBoolean(props.getProperty("GlobalStructure"));
        p.isSeparable = Boolean.parseBoolean(props.getProperty("Separable"));
    }

    public void setEvaluation(ContestEvaluation evaluation)
    {
        // Set evaluation problem used in the run
        this.evaluation = evaluation;

        // Get evaluation properties
        Properties props = evaluation.getProperties();
        setProperties(props);

        if (p.isMultimodal)
        {
            // change settings for "multimodal"-data
        }

        if (p.hasStructure)
        {
            // change settings for a "global structure"
        }

        if (p.isSeparable)
        {
            // change settings for "separable"-data
        }
    }

    public void run()
    {
        //Stay within the amount of tries set by the server
        System.out.println("Start Population");
<<<<<<< HEAD

        while(counter < eval_limit)
=======
        
        // Hier zou k niet de counter gebruiken maar gewoon een standaard for-loop maken
        while(counter < p.population_size)
>>>>>>> 70d5c6892a393788e74500f1d16b402f96dc484e
        {
            for(int g = 0; g < p.population_size; g++)
            {
                double fitness = (Double)evaluation.evaluate(population.get(g).getVector());
                population.get(g).setFitness(fitness);
                Collections.sort(population);
                System.out.println(population.get(g).getFitness());
                counter++;
            }

        //Create the parent population
        // Hier zou ik pas na de for-loop sorteren
        for(int i = 0; i < parent_size; i++)
        {
            parent_population.add(population.get(i));
        }
        Collections.sort(parent_population);

        System.out.println("Child Population");
        //Create the child population by applying crossover on the parent population
<<<<<<< HEAD
        for(int j = 1; j < parent_population.size(); j++)
=======
        // Hier loopt ie dus out of bounds, moeten we even kijken hoe we dat oplossen.
        // println aangepast
        for(int j = 0; j < parent_population.size(); j++)
>>>>>>> 70d5c6892a393788e74500f1d16b402f96dc484e
        {
            child_population.add(parent_population.get(j).crossover(parent_population.get(j-1),p));
            child_population.add(parent_population.get(j-1).crossover(parent_population.get(j),p));
            Individual evaluation_child = child_population.get(j);
            double fitness = (Double)evaluation.evaluate(evaluation_child.getVector());
            evaluation_child.setFitness(fitness);
            counter++;
            System.out.println(child_population.get(j).getFitness());
        }
        Collections.sort(child_population);
    }
}
<<<<<<< HEAD
}
=======
>>>>>>> 70d5c6892a393788e74500f1d16b402f96dc484e
