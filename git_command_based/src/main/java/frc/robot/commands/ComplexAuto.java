package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ComplexAuto extends SequentialCommandGroup {



    public ComplexAuto() {


        addCommands(    
            new DriveForward(1.2), // --> avanza 1.2m
            new NeitoPID(10), // --> gira 10 veces
            
            new ParallelCommandGroup(
                new DriveForward(2.2), new NeitoPID(5) // avanza otro metro.
            ),

            new DriveForward(3.2) // avanza otro metro.

        );  

        /* no jsló */
    /* Los setpoints de distancia son acumulativos, es decir
        que si quiero que el chasis avance 2 metros en dos momentos, 
        puedo mandarle que avance 1 metro y después 2 metros. Si le mando
        1 metro y después 1 metro, se va a buggear pensando que esa distancia 
        ya la recorrió y no iniciará el comando.
     */
    }


    
}
