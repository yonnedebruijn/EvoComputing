// An individual encodes a single vector of numbers
public class Individual {
    public double vector[];
    private double fitness;


    public Individual (Rand c, Parameters p)
    {
        vector = new double[p.vector_length];
        fitness = 0;
        for(int i = 0; i < p.vector_length; i++)
        {
            vector[i] = c.rnd();
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

    public Individual crossover(Individual ind, Parameters p)
    {
        final int coPoint = 5; // must be set to random within a range
        Individual child = new Individual(p);

        for(int i = 0; i < coPoint;  i++) {
            child.vector[i] = ind.vector[i];
        }

        for (int i = coPoint; i < vector.length; i++){
            child.vector[i] = this.vector[i];
        }

        return child;

    }

    public void mutate(Rand c, java.util.Random r, Parameters p)
    {
        for(int i = 0; i < p.vector_length; i++)
        {
            if(r.nextDouble() < p.mutation_rate)
            {
                double noise = c.rnd();
                while(noise < p.lr_mutation_range || noise > p.up_mutation_range)
                    noise = c.rnd();
                vector[i] += noise;
            }
        }
    }
}
