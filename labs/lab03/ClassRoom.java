package lab03;

import java.util.List;
import java.util.ArrayList;

public class ClassRoom
{
    private List<Student> roster = new ArrayList<Student>();

    public void addStudent(Student s)
    {
        roster.add(s);
    }

    public boolean dropStudent(int id)
    {
        for (int i = 0; i < roster.size(); ++i)
        {
            var s = roster.get(i);

            if (s.getId() == id)
            {
                roster.remove(i);

                return true;
            }
        }

        return false;
    }

    public void sortById()
    {
        for (int i = 0; i < roster.size(); ++i)
        {
            for (int j = 1; j < roster.size() - i; ++j)
            {
                var high = roster.get(j);
                var low = roster.get(j - 1);

                if (low.getId() > high.getId())
                {
                    roster.set(j, low);
                    roster.set(j - 1, high);
                }
            }
        }
    }

    public List<Student> getRoster()
    {
        return this.roster;
    }

    public String toString()
    {
        return roster.toString();
    }
}
