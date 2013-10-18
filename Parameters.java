public class Parameters {
    // algorithm parameters
    public double mutation_rate = 0.4; // how often a mutation takes place
    public int population_size = 50;
    public int vector_length = 10;

    // lower and upper range the mutation-values can take
    public double lr_mutation_range = -0.5;
    public double up_mutation_range = 0.5;

    // lower and upper range the vector-numbers can take
    public double lr_vector_range = -5;
    public double up_vector_range = 5;

    // sizes of the parent and elite portion in %
    public double parent_portion = 0.4;
    public double elite_portion = 0.2;



    // data parameters
    public boolean isMultimodal = false;     // if the data has several peaks in the distribution
    public boolean hasStructure = false;
    public boolean isSeparable = false;      // if the data is linear separable


}
