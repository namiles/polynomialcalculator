package project2;

/*
 * 
 * Worked with James Rhinehart on this project.
 * Workload split evenely, both doing about 50% of the project. Collaborated on most of it via Discord.
 * 
 */

public interface Scalar {
	public int getNum();
	public int getDenom();
    public double getVal();
    public void setVal(double n);
    public Scalar add(Scalar s);
    public Scalar mult(Scalar s);
    public Scalar mult(int s);
    public Scalar pow(int exp);
    public Scalar neg();
    public boolean equals(Scalar s);
    public boolean equals(int s);
    public String giveUsString();
}
	