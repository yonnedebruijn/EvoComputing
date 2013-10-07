import org.vu.contest.ContestSubmission;
import org.vu.contest.ContestEvaluation;

import java.util.Random;
import java.util.Properties;

public class Player13 implements ContestSubmission{
    Random rand;
    ContestEvaluation evaluation;
    final Parameters p = new Parameters();

    private int eval_limit;

    public Player13 ()
    {
        rand = new Random();
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
        // Run your algorithm here

        // Getting data from evaluation problem (depends on the specific evaluation implementation)
        // E.g. getting a vector of numbers
        // Vector<Double> data = (Vector<Doulbe>)evaluation_.getData("trainingset1");

        // Evaluating your results
        // E.g. evaluating a series of true/false predictions
        // boolean pred[] = ...
        // Double score = (Double)evaluation_.evaluate(pred);
    }
}