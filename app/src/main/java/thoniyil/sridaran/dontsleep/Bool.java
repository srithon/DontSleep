package thoniyil.sridaran.dontsleep;

public class Bool
{
    private boolean state;

    public Bool(boolean initialState)
    {
        state = initialState;
    }

    public void toggle()
    {
        state = !state;
    }

    public void set(boolean state)
    {
        this.state = state;
    }

    public boolean getState()
    {
        return state;
    }
}
