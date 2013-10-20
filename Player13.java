import org.vu.contest.ContestSubmission;
import org.vu.contest.ContestEvaluation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Properties;


public class Player13 implements ContestSubmission{
    Random rand;

    private ArrayList<Individual> population;
    private ArrayList<Individual> parent_population;
    private ArrayList<Individual> child_population;

    ContestEvaluation evaluation;
    final Parameters p = new Parameters();

    private int eval_limit;     // amount of allowed tries on the server
    private int counter = 0;    // counter that represents the amount of evaluated tries
    final private int parent_size = (int) (p.parent_portion * p.population_size);

    public Player13 ()
    {
        rand = new Random();

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
        //Create the populations of 'individuals'
        population = new ArrayList<Individual>(p.population_size);

        for(int i = 0; i < p.population_size; i++)
        {
            population.add(new Individual(rand,p));
        }

        //while(counter < eval_limit){
            for(int g = 0; g < population.size(); g++)
            {
                double fitness = (Double)evaluation.evaluate(population.get(g).getVector());
                population.get(g).setFitness(fitness);
                System.out.println(g + ", " + population.get(g).getFitness());
                counter++;
            }
            Collections.sort(population);

            System.out.println("Parent Population");
            //Create the parent population
            int parent_size = (int) (p.parent_portion * population.size());
            parent_population = new ArrayList<Individual>(parent_size);
            for(int i = 0; i < parent_size; i++)
            {
                parent_population.add(population.get(i));
                System.out.println(i + ", " + parent_population.get(i).getFitness());
            }
            Collections.sort(parent_population);

            System.out.println("Child Population");
            child_population = new ArrayList<Individual>(2*parent_size);
            System.out.println("Elite childs:");
            for(int j = 1; j <= (int)(parent_size * p.elite_portion); j++)
            {
                Individual parent_1 = parent_population.get(j-1);
                Individual parent_2 = parent_population.get(j);
                Individual child = parent_1.crossover(parent_2,p);
<<<<<<< HEAD
                child.mutate(rand,p);
=======
                child_population.add(child);
>>>>>>> c7dca25287a13bfabcd15bb319c67abaa9c9c8a2
                child.setFitness((Double)evaluation.evaluate(child.getVector()));
                System.out.println(j-1+","+child.getFitness());
            }
            population.addAll(child_population);
            Collections.sort(population);

<<<<<<< HEAD
=======


            /*
            //Create the child population by applying crossover on the parent population
            for(int j = 1; j < parent_population.size(); j++)
            {
                child_population.add(parent_population.get(j).crossover(parent_population.get(j-1),p));
                child_population.add(parent_population.get(j-1).crossover(parent_population.get(j),p));
                Individual evaluation_child = child_population.get(j);
                double fitness = (Double)evaluation.evaluate(evaluation_child.getVector());
                evaluation_child.setFitness(fitness);
                counter++;
            }
            Collections.sort(child_population);
            */

        //}
>>>>>>> c7dca25287a13bfabcd15bb319c67abaa9c9c8a2
    }
}
}