import java.util.Random;

// An individual encodes a single vector of numbers
public class Individual implements Comparable<Individual> {
    private double vector[];
    private double fitness;


    public Individual (Random c, Parameters p)
    {
        vector = new double[p.vector_length];
        fitness = 0;
        for(int i = 0; i < p.vector_length; i++)
        {
            double vector_value = p.lr_vector_range + (c.nextDouble()*((p.up_vector_range-p.lr_vector_range)+1));
            vector[i] = vector_value;
        }

    }

    public Individual (Parameters p)
    {
        vector = new double[p.vector_length];
        fitness  = 0;
        for(int i = 0; i < p.vector_length; i++)
        {
            vector[i] = 0;
        }
    }

    public void setFitness(double fitness)
    {
        this.fitness = fitness;
    }

    public double getFitness()
    {
        return this.fitness;
    }

    public double[] getVector()
    {
        return this.vector;
    }

    public Individual crossoverI(Individual ind, Parameters p)
    {

        final int coPoint = 2 + (int)(Math.random() * ((9 - 2) + 1)); // random value within [2,9], thus excluding the first and last vector_value
        Individual child = new Individual(p);

        for(int i = 0; i < coPoint;  i++) {
            child.vector[i] = ind.vector[i];
        }

        for (int i = coPoint; i < vector.length; i++){
            child.vector[i] = this.vector[i];
        }

        return child;

    }

    public Individual crossoverII(Individual ind, Parameters p)
    {
        Individual child = new Individual(p);
        for(int i = 0; i < p.vector_length; i++)
        {
            child.vector[i] = (this.vector[i] + ind.getVector()[i]) / 2;
        }
        return child;
    }

    public void mutateI(Random c, Parameters p)
    {
        for(int i = 0; i < p.vector_length; i++)
        {
            if(c.nextDouble() < p.mutation_rate)
            {
                double noise = c.nextDouble();
                while(noise < p.lr_mutation_range || noise > p.up_mutation_range)
                    noise = c.nextDouble();
                vector[i] += noise;
            }
        }

    }

    public void mutateII(Random c, Parameters p,int min, int max)
    {
        for(int i = 0; i < p.vector_length; i++)
        {
            if(c.nextDouble() < p.mutation_rate)
            {
                double noise = min + (c.nextDouble()*(max-min)+1);
                vector[i] += noise;
            }
        }

    }

    @Override
    public int compareTo(Individual i) {
        return getFitness() < i.getFitness() ? 1 : getFitness() > i.getFitness() ? -1 : 0;
    }
}
