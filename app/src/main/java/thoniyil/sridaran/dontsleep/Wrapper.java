package thoniyil.sridaran.dontsleep;

public class Wrapper<E>
{
    private E object;

    public Wrapper(E object)
    {
        this.object = object;
    }

    public void set(E object)
    {
        this.object = object;
    }

    public E get()
    {
        return object;
    }
}
