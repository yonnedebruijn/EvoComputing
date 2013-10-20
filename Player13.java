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
		System.out.println("Evalutation limit = "+eval_limit);
		p.isMultimodal = Boolean.parseBoolean(props.getProperty("Multimodal"));
		System.out.println("Multimodal = "+p.isMultimodal);
		p.hasStructure = Boolean.parseBoolean(props.getProperty("GlobalStructure"));
		System.out.println("Global structure = "+p.hasStructure);
		p.isSeparable = Boolean.parseBoolean(props.getProperty("Separable"));
		System.out.println("Separable = "+p.isSeparable);
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
			double fitness = (Double)evaluation.evaluate(population.get(i).getVector());
			population.get(i).setFitness(fitness);
			System.out.println(i + ", " + population.get(i).getFitness());
			counter++;
		}

		while(counter < eval_limit){
			for(int g = 0; g < population.size(); g++)
				Collections.sort(population);
			ArrayList<Individual> new_population = new ArrayList<Individual>(p.population_size);
			for(int g = 0; g < p.population_size; g++)
			{
				new_population.add(population.get(g));
			}
			Collections.sort(population);
			population = new_population;
			System.out.println(population.size());
			System.out.println("Parent Population");
			//Create the parent population
			int parent_size = (int) (p.parent_portion * population.size());
			parent_population = new ArrayList<Individual>(parent_size);
			for(int i = 0; i < parent_size; i++)
			{
				parent_population.add(population.get(i));
				System.out.println(i + ", " + parent_population.get(i).getFitness());
			}

			System.out.println("Child Population");
			child_population = new ArrayList<Individual>(2*parent_size);
			System.out.println("Elite childs:");

			for(int j = 1; j < (int) (2*parent_size*p.elite_portion); j++)
			{
				Individual parent_1 = parent_population.get(j-1);
				Individual parent_2 = parent_population.get(j);
				Individual child1 = parent_1.crossoverII(parent_2,p);
				child_population.add(child1);
				child1.setFitness((Double)evaluation.evaluate(child1.getVector()));
				counter++;
				System.out.println(j-1+","+child1.getFitness());

				Individual child2 = parent_2.crossoverII(parent_1,p);
				child_population.add(child2);
				child2.setFitness((Double)evaluation.evaluate(child2.getVector()));
				counter++;
				System.out.println(j-1+","+child2.getFitness());
			}

			System.out.println("Common childs:");
			while (child_population.size() < parent_size){
				Individual parent_1 = parent_population.get(child_population.size()-1);
				Individual parent_2 = parent_population.get(child_population.size());
				Individual child = parent_1.crossoverII(parent_2,p);
				child.mutate(rand,p);
				child.setFitness((Double)evaluation.evaluate(child.getVector()));
				child_population.add(child);
				System.out.println(child_population.size()-1+","+child.getFitness());
				counter++;
				System.out.println(child_population.size()-1+","+child.getFitness());  	
			}
			population.addAll(child_population);
			Collections.sort(population);
			System.out.println(population.size());

	}
}
}
