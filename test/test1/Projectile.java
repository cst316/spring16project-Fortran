import java.io.*;

public class Projectile {
    public static void main (String[] args) throws IOException {
        
        InputStreamReader reader = new InputStreamReader (System.in);
        BufferedReader stdin = new BufferedReader (reader);
        
        System.out.println ("Enter the velocity (ft / sec), angle (degrees), distance (ft), target size (ft), elevation of target (ft) (Please Enter a space after each number)");
        
        String UserInput = stdin.readLine();
        String traj [] = UserInput.split ("()\\s+");
        
        double Velo = Double.parseDouble (traj [0]);
        double Angle = Double.parseDouble (traj [1]);
        double Dist = Double.parseDouble (traj [2]);
        double Targsize = Double.parseDouble (traj [3]);
        double Elevation = Double.parseDouble (traj [4]);
                   
        while (Velo != 0 && Angle != 0 && Dist != 0 && Targsize != 0 && Elevation != 0){
              
            double Rad = Angle * (Math.PI / 180);
            double Grav = 32.17;
            
            double Time = (Dist / (Velo * Math.cos(Rad)));
            double Height = (Velo * Time * Math.sin(Rad)) - ((Grav * Time * Time) / 2);
            double RoundHeight = (double) Math.round(Height * 1000) / 1000;
            double RoundVelo = (double) Math.round(Velo * 1000) / 1000;
            double RoundAngle = (double) Math.round(Angle * 1000) / 1000;
            double RoundDist = (double) Math.round(Dist * 1000) / 1000;
            double RoundTargsize = (double) Math.round(Targsize * 1000) / 1000;
            double RoundElevation = (double) Math.round(Elevation * 1000) / 1000;
            
            if (Height < Targsize && Height > Elevation){
                
                System.out.println ("The projectile's velocity is " + RoundVelo + " feet per second");
                System.out.println ("The angle of elevation is " + RoundAngle + " degrees");
                System.out.println ("The distance to the target is " + RoundDist + " feet");
                System.out.println ("The target's size is " + RoundTargsize + " feet");
                System.out.println ("The target is located " + RoundElevation + " feet above the ground");
                System.out.println ("The target was hit by the projectile.");
                
             } else if (Height > Targsize){
                 
                System.out.println ("The projectile's velocity is " + RoundVelo + " feet per second");
                System.out.println ("The angle of elevation is " + RoundAngle + " degrees");
                System.out.println ("The distance to the target is " + RoundDist + " feet");
                System.out.println ("The target's size is " + RoundTargsize + " feet");
                System.out.println ("The target is located " + RoundElevation + " feet above the ground");
                System.out.println ("The projectile was too high, height was: " + RoundHeight + " feet.");
                
            } else if (Height < Elevation && Height > 0){
                
                System.out.println ("The projectile's velocity is " + RoundVelo + " feet per second");
                System.out.println ("The angle of elevation is " + RoundAngle + " degrees");
                System.out.println ("The distance to the target is " + RoundDist + " feet");
                System.out.println ("The target's size is " + RoundTargsize + " feet");
                System.out.println ("The target is located " + RoundElevation + " feet above the ground");
                System.out.println ("The projectile was too low, height was: " + RoundHeight + " feet.");
                
            } else {
                
                System.out.println ("The projectile's velocity is " + RoundVelo + " feet per second");
                System.out.println ("The angle of elevation is " + RoundAngle + " degrees");
                System.out.println ("The distance to the target is " + RoundDist + " feet");
                System.out.println ("The target's size is " + RoundTargsize + " feet");
                System.out.println ("The target is located " + RoundElevation + " feet above the ground");
                System.out.println ("The computed distance was too short to reach the target.");
                
            }
            
            UserInput = stdin.readLine();
            traj = UserInput.split ("()\\s+");
            
            Velo = Double.parseDouble (traj [0]);
            Angle = Double.parseDouble (traj [1]);
            Dist = Double.parseDouble (traj [2]);
            Targsize = Double.parseDouble (traj [3]);
            Elevation = Double.parseDouble (traj [4]);
        }
        
            
    
    
    }
}