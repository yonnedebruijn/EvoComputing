import org.vu.contest.ContestSubmission;
import org.vu.contest.ContestEvaluation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Properties;


public class Player13 implements ContestSubmission{
    Random rand;

    private static final Rand cauchy = new Rand.Cauchy(0d,1d);

    final private ArrayList<Individual> population;
    final private ArrayList<Individual> elite_population;
    final private ArrayList<Individual> parent_population;

    ContestEvaluation evaluation;
    final Parameters p = new Parameters();

    private int eval_limit;
    final private int parent_size = (int) p.parent_portion * p.population_size;
    final private int elite_size = (int) p.elite_portion * p.population_size;
    public Player13 ()
    {
        rand = new Random();
        //Create the populations of 'individuals'
        population = new ArrayList<Individual>(p.population_size);
        elite_population = new ArrayList<Individual>(elite_size);
        parent_population = new ArrayList<Individual>(parent_size);
        for(int i = 0; i < p.population_size; i++)
        {
            population.add(new Individual(cauchy,p));
        }

    }


    public void setSeed(long seed)
    {
        // Set seed of algortihms random process
        rand.setSeed(seed);
    }

    public void setProperties(Properties props)     // writes the evaluation problems' properties to the parameters-file
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
        for(int i = 0; i < eval_limit; i++)
        {
            population.get(i).setFitness((Double) evaluation.evaluate(population.get(i)));
            Collections.sort(population);

        }

    }
}