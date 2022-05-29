import java.awt.*;
import java.util.*;
import org.jfree.chart.*;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.*;

public class task {
    static class Pair{
        public   int first;
        public    int second;
        Pair(int x,int y)
        {
            first=x;
            second=y;
        }
    }
    static class LineChartEx extends JFrame {


        public void initUI(Vector<Pair> points,String schedulingName) {

            XYDataset dataset = createDataset(points,schedulingName);
            JFreeChart chart = createChart(dataset);

            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            chartPanel.setBackground(Color.GRAY);
            add(chartPanel);

            pack();
            setTitle("Disk Scheduling");
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        private XYDataset createDataset(Vector<Pair> points,String schedulingName) {
            int timer= 0;
            var series = new XYSeries(schedulingName);
            for(int i=0;i<points.size();i++) {
                series.add(timer, points.get(i).first);
                timer++;
            }
            series.add(timer, points.get(points.size()-1).second);
            var dataset = new XYSeriesCollection();
            dataset.addSeries(series);

            return dataset;
        }

        private JFreeChart createChart(XYDataset dataset) {

            JFreeChart chart = ChartFactory.createXYLineChart(
                    "Visualizing the Sequence of the Head Movements",
                    "Time",
                    "Cylinder Number",
                    dataset,
                    PlotOrientation.VERTICAL,
                    true,
                    true,
                    false
            );

            XYPlot plot = chart.getXYPlot();

            var renderer = new XYLineAndShapeRenderer();
            renderer.setSeriesPaint(0, Color.BLUE);
            renderer.setSeriesStroke(0, new BasicStroke(2.0f));

            plot.setRenderer(renderer);
            plot.setBackgroundPaint(Color.white);

            plot.setRangeGridlinesVisible(true);
            plot.setRangeGridlinePaint(Color.BLACK);

            plot.setDomainGridlinesVisible(true);
            plot.setDomainGridlinePaint(Color.BLACK);

            chart.getLegend().setFrame(BlockBorder.NONE);

            chart.setTitle(new TextTitle("Visualizing the Sequence of the Head Movements",
                            new Font("Serif", java.awt.Font.BOLD, 18)
                    )
            );

            return chart;
        }
    }
    public static void main (String [] args)
    {
        Scanner in=new Scanner(System.in);
        int choice , choice1 ,numberOfRequest , initialHeadStartCylinder, total;
        String direction;
        System.out.println("Enter Number of Requests");
        numberOfRequest = in.nextInt();
        int []array=new int [numberOfRequest];
        System.out.println("Enter the number of each cylinder");
        for(int i=0;i<8;i++)
        {
            array[i]= in.nextInt();
        }
        System.out.println("Enter the initial head start cylinder");
        initialHeadStartCylinder = in.nextInt();
        System.out.println("Enter the total Number of cylinders");
        total = in.nextInt();
        System.out.println("Enter the direction");
        direction = in.next();
        Vector<Pair>ans=new Vector<Pair>();
        while(true) {
            System.out.println("Please Choice The Algorithm by typing its number");
            System.out.println("Type 1 to FCFS");
            System.out.println("Type 2 to SCAN");
            System.out.println("Type 3 to CSCAN");
            System.out.println("Type 4 to LOOK");
            System.out.println("Type 5 to CLOOK");
            System.out.println("Type 6 to SSTF");
            System.out.println("Type 7 to NEW OPTIMIZED ALGORITHM");
            System.out.println("Type 0 to exit");
            choice = in.nextInt();
            if (choice == 1) {
                ans = fcfs(array, numberOfRequest, initialHeadStartCylinder, total);
                Vector<Pair> finalAns = ans;
                System.out.println("Do you want visualize the sequence of head movements? \n 1-Yes 2-NO");
                choice1=in.nextInt();
                if(choice1==1){
                EventQueue.invokeLater(() -> {
                    var ex = new LineChartEx();
                    ex.initUI(finalAns,"FCFS");
                    ex.setVisible(true);
                });}
                else{continue;}
            }
            else if (choice == 2) {
                ans = scan(array, numberOfRequest, initialHeadStartCylinder, direction, total);
                Vector<Pair> finalAns = ans;
                System.out.println("Do you want visualize the sequence of head movements? \n 1-Yes 2-NO");
                choice1=in.nextInt();
                if(choice1==1){
                    EventQueue.invokeLater(() -> {
                        var ex = new LineChartEx();
                        ex.initUI(finalAns,"SCAN");
                        ex.setVisible(true);
                    });}
                else{continue;}
            }
            else if (choice == 3) {
                ans = cscan(array, numberOfRequest, initialHeadStartCylinder, direction, total);
                Vector<Pair> finalAns = ans;
                System.out.println("Do you want visualize the sequence of head movements? \n 1-Yes 2-NO");
                choice1=in.nextInt();
                if(choice1==1){
                    EventQueue.invokeLater(() -> {
                        var ex = new LineChartEx();
                        ex.initUI(finalAns,"CSCAN");
                        ex.setVisible(true);
                    });}
                else{continue;}
            }
            else if (choice == 4) {
                ans = Look(array, numberOfRequest, initialHeadStartCylinder, direction, total);
                Vector<Pair> finalAns = ans;
                System.out.println("Do you want visualize the sequence of head movements? \n 1-Yes 2-NO");
                choice1=in.nextInt();
                if(choice1==1){
                    EventQueue.invokeLater(() -> {
                        var ex = new LineChartEx();
                        ex.initUI(finalAns,"LOOK");
                        ex.setVisible(true);
                    });}
                else{continue;}
            }
            else if (choice == 5) {
                ans = cLook(array, numberOfRequest, initialHeadStartCylinder, direction, total);
                Vector<Pair> finalAns = ans;
                System.out.println("Do you want visualize the sequence of head movements? \n 1-Yes 2-NO");
                choice1=in.nextInt();
                if(choice1==1){
                    EventQueue.invokeLater(() -> {
                        var ex = new LineChartEx();
                        ex.initUI(finalAns,"CLOOK");
                        ex.setVisible(true);
                    });}
                else{continue;}
            }
            else if (choice == 6) {
                ans = sstf(array, numberOfRequest, initialHeadStartCylinder, total);
                Vector<Pair> finalAns = ans;
                System.out.println("Do you want visualize the sequence of head movements? \n 1-Yes 2-NO");
                choice1=in.nextInt();
                if(choice1==1){
                    EventQueue.invokeLater(() -> {
                        var ex = new LineChartEx();
                        ex.initUI(finalAns,"SSTF");
                        ex.setVisible(true);
                    });}
                else{continue;}
            }
            else if (choice == 7) {
                ans = newlyOptimized(array, numberOfRequest);
                Vector<Pair> finalAns = ans;
                System.out.println("Do you want visualize the sequence of head movements? \n 1-Yes 2-NO");
                choice1=in.nextInt();
                if(choice1==1){
                    EventQueue.invokeLater(() -> {
                        var ex = new LineChartEx();
                        ex.initUI(finalAns,"NEW OPTIMIZED ALGORITHM");
                        ex.setVisible(true);
                    });}
                else{continue;}
            } else {
                break;
            }
        }
    }
    // {176, 79, 34, 60, 92, 11, 41, 114}
    //50
    static Vector<Pair> fcfs(int[] array, int size,int start,int total)
    {
        Vector<Pair>operations=new Vector<Pair>();
        int ans=0;
        ans+=Math.abs(start-array[0]);
        Pair x =new Pair(start,array[0]);
        operations.add(x);
        System.out.println(start+" -> "+array[0]);
        for(int i=0;i<size-1;i++)
        {
            ans+=Math.abs(array[i]-array[i+1]);
            x =new Pair(array[i],array[i+1]);
            operations.add(x);
            System.out.println(array[i]+" -> "+array[i+1]);
        }
        System.out.println(ans);
        return  operations;
    }

    static Vector<Pair> cLook(int[] array, int size,int start,String direction,int total)
    {
        Vector<Pair>operations=new Vector<Pair>();
        int ans=0;
        int current =start;
        if(direction.equals("right"))
        {

            for(int i=start+1;i<=total;i++)
            {
                for(int j=0;j<size;j++)
                {
                    if(array[j]==i)
                    {
                        ans+=Math.abs(current-array[j]);
                        Pair x =new Pair(current,array[j]);
                        operations.add(x);
                        System.out.println(current+" -> "+array[j]);
                        current=array[j];
                        break;
                    }
                }
            }

            for(int i=0;i<=start;i++)
            {
                for(int j=0;j<size;j++)
                {
                    if(array[j]==i)
                    {
                        ans+=Math.abs(current-array[j]);
                        Pair x =new Pair(current,array[j]);
                        operations.add(x);
                        System.out.println(current+" -> "+array[j]);
                        current=array[j];
                        break;
                    }
                }
            }
        }
        else {

            for(int i=start+1;i>=0;i--)
            {
                for(int j=0;j<size;j++)
                {
                    if(array[j]==i)
                    {
                        ans+=Math.abs(current-array[j]);
                        Pair x =new Pair(current,array[j]);
                        operations.add(x);
                        System.out.println(current+" -> "+array[j]);
                        current=array[j];
                        break;
                    }
                }
            }

            for(int i=total;i>=start;i--)
            {
                for(int j=0;j<size;j++)
                {
                    if(array[j]==i)
                    {
                        ans+=Math.abs(current-array[j]);
                        Pair x =new Pair(current,array[j]);
                        operations.add(x);
                        System.out.println(current+" -> "+array[j]);
                        current=array[j];
                        break;
                    }
                }
            }



        }


        System.out.println(ans);
        return operations;

    }

    static Vector<Pair> Look(int[] array, int size,int start,String direction,int total)
    {
        Vector<Pair>operations=new Vector<Pair>();
        int ans=0;
        int current =start; //53
        if(direction.equals("right"))
        {

            for(int i=start+1;i<=total;i++) //from 54 to 200
            {
                for(int j=0;j<size;j++)
                {
                    if(array[j]==i)
                    {
                        ans+=Math.abs(current-array[j]);
                        Pair x =new Pair(current,array[j]);
                        operations.add(x);
                        System.out.println(current+" -> "+array[j]);
                        current=array[j];
                        break;
                    }
                }
            }

            for(int i=start;i>=0;i--)
            {
                for(int j=0;j<size;j++)
                {
                    if(array[j]==i)
                    {
                        ans+=Math.abs(current-array[j]);
                        Pair x =new Pair(current,array[j]);
                        operations.add(x);
                        System.out.println(current+" -> "+array[j]);
                        current=array[j];
                        break;
                    }
                }
            }
        }
        else {

            for(int i=start+1;i>=0;i--)
            {
                for(int j=0;j<size;j++)
                {
                    if(array[j]==i)
                    {
                        ans+=Math.abs(current-array[j]);
                        Pair x =new Pair(current,array[j]);
                        operations.add(x);
                        System.out.println(current+" -> "+array[j]);
                        current=array[j];
                        break;
                    }
                }
            }

            for(int i=start+1;i<=total;i++)
            {
                for(int j=0;j<size;j++)
                {
                    if(array[j]==i)
                    {
                        ans+=Math.abs(current-array[j]);
                        Pair x =new Pair(current,array[j]);
                        operations.add(x);
                        System.out.println(current+" -> "+array[j]);
                        current=array[j];
                        break;
                    }
                }
            }



        }


        System.out.println(ans);
        return operations;

    }

    public static Vector<Pair> sstf (int[] array, int size,int start,int total)
    {
        Vector<Pair>operations=new Vector<Pair>();
        int ans=Integer.MAX_VALUE;

        int it=0;
        for(int i=0; i<size; i++)
        {
            if(ans > Math.abs(array[i]-start))
            {
                ans = Math.abs(array[i]-start);
                it = i;
            }
        }
        int temp = array[0];
        array[0] = array[it];
        array[it] = temp;

        Pair x= new Pair(start, array[0]);
        operations.add(x);
        System.out.println(start+"  -> "+array[0]);
        int shortest = Integer.MAX_VALUE;
        int iter=0;
        for (int i=0; i<size-1; i++)
        {
            for(int j=i+1; j < size; j++)
            {
                if(Math.abs(array[i] - array[j]) < shortest)
                {
                    shortest = Math.abs(array[j] - array[i]);
                    iter = j;
                }
            }
            ans+=shortest;
            x = new Pair(array[i], array[iter]);
            operations.add(x);
            System.out.println(array[i]+"  -> "+array[iter]);

            int temp_2 = array[i+1]; //183
            array[i+1] = array[iter]; // 67
            array[iter] = temp_2; // 183

            iter=0;
            shortest = Integer.MAX_VALUE;
        }
        System.out.println("Total movement = "+ans);
        return  operations;

    }

    static Vector<Pair> scan(int[] array, int size,int start,String direction,int total)
    {
        Vector<Pair>operations=new Vector<Pair>();
        int ans=0;
        int current =start; // current = 53
        if(direction.equals("right"))
        {
            for(int i=start+1;i<=total;i++) // from 54 to 200
            {
                for(int j=0;j<size;j++)
                {
                    if(array[j]==i)
                    {
                        ans+=Math.abs(current-array[j]);
                        Pair x =new Pair(current,array[j]);
                        operations.add(x);
                        System.out.println(current+"  -> "+array[j]);
                        current=array[j];
                        break;
                    }
                }
            }

            Pair x = new Pair(current,total-1);
            operations.add(x);
            System.out.println(current+"  -> "+(total-1));
            ans+=Math.abs(current-(total-1));
            current = total-1;

            for(int i=start;i>=0;i--)
            {
                for(int j=0;j<size;j++)
                {
                    if(array[j]==i)
                    {
                        ans+=Math.abs(current-array[j]);
                        x =new Pair(current,array[j]);
                        operations.add(x);
                        System.out.println(current+"  -> "+array[j]);
                        current=array[j];
                        break;
                    }
                }
            }
        }
        else {

            for(int i=start+1;i>=0;i--)
            {
                for(int j=0;j<size;j++)
                {
                    if(array[j]==i)
                    {
                        ans+=Math.abs(current-array[j]);
                        Pair x =new Pair(current,array[j]);
                        operations.add(x);
                        System.out.println(current+"  -> "+array[j]);
                        current=array[j];
                        break;
                    }
                }
            }

            Pair x = new Pair(current,0);
            operations.add(x);
            System.out.println(current+"  -> "+0);
            ans+=current;
            current = 0;

            for(int i=start+1;i<=total;i++)
            {
                for(int j=0;j<size;j++)
                {
                    if(array[j]==i)
                    {
                        ans+=Math.abs(current-array[j]);
                        x =new Pair(current,array[j]);
                        operations.add(x);
                        System.out.println(current+"  -> "+array[j]);
                        current=array[j];
                        break;
                    }
                }
            }
        }
        System.out.println("Total movements = "+ans);
        return operations;
    }

    static Vector<Pair> cscan(int[] array, int size,int start,String direction,int total)
    {
        Vector<Pair>operations=new Vector<Pair>();
        int ans=0;
        int current =start;
        if(direction.equals("right"))
        {
            for(int i=start+1;i<=total;i++)
            {
                for(int j=0;j<size;j++)
                {
                    if(array[j]==i)
                    {
                        ans+=Math.abs(current-array[j]);
                        Pair x =new Pair(current,array[j]);
                        operations.add(x);
                        System.out.println(current+"  -> "+array[j]);
                        current=array[j];
                        break;
                    }
                }
            }

            Pair x = new Pair(current,total-1);
            operations.add(x);
            System.out.println(current+"  -> "+(total-1));
            ans+=Math.abs(current-(total-1));
            current = total-1;

            x = new Pair(current,0);
            operations.add(x);
            System.out.println(current+"  -> "+0);
            ans+=current;
            current = 0;

            for(int i=0;i<=start;i++)
            {
                for(int j=0;j<size;j++)
                {
                    if(array[j]==i)
                    {
                        ans+=Math.abs(current-array[j]);
                        x =new Pair(current,array[j]);
                        operations.add(x);
                        System.out.println(current+"  -> "+array[j]);
                        current=array[j];
                        break;
                    }
                }
            }
        }
        else {

            for(int i=start+1;i>=0;i--)
            {
                for(int j=0;j<size;j++)
                {
                    if(array[j]==i)
                    {
                        ans+=Math.abs(current-array[j]);
                        Pair x =new Pair(current,array[j]);
                        operations.add(x);
                        System.out.println(current+"  -> "+array[j]);
                        current=array[j];
                        break;
                    }
                }
            }

            Pair x = new Pair(current,0);
            operations.add(x);
            System.out.println(current+"  -> "+0);
            ans+=current;
            current = 0;

            x = new Pair(current,total-1);
            operations.add(x);
            System.out.println(current+"  -> "+(total-1));
            ans+=Math.abs(current-(total-1));
            current = total-1;

            for(int i=total;i>=start;i--)
            {
                for(int j=0;j<size;j++)
                {
                    if(array[j]==i)
                    {
                        ans+=Math.abs(current-array[j]);
                        x =new Pair(current,array[j]);
                        operations.add(x);
                        System.out.println(current+"  -> "+array[j]);
                        current=array[j];
                        break;
                    }
                }
            }
        }
        System.out.println(ans);
        return operations;
    }
    /*We initiallize the start head postition equal to zero.
     -Sort the queue of requests ascendingly
     -Service the input request in begin of head instantly.
     -Calculate the total head movements.*/

    public static Vector<Pair> newlyOptimized (int[] array, int size)
    {
        Vector<Pair>operations=new Vector<Pair>();
        for (int i=0; i<size-1; i++)
        {
            for(int j=i+1; j < size; j++)
            {
                if(array[j]<array[i])
                {
                    int temp;
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        int ans=0;
        for (int i=0; i<size-1; i++)
        {
            if(i==0)
            {
                System.out.println(0+"  -> "+array[0]);
            }
            else {
                ans += Math.abs(array[i] - array[i + 1]);
                Pair x = new Pair(array[i], array[i + 1]);
                operations.add(x);
                System.out.println(array[i]+"  ->  "+ array[i + 1]);
            }
        }
        System.out.println("Total movement = "+ans);
        return  operations;
    }


}

/*
176
79
34
60
92
11
41
114
*/
/*98 183 37 122 14 124 65 67*/