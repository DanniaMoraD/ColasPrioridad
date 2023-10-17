import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Clinica {
    private PriorityQueue<Paciente> clinica;
    public Clinica(){
        clinica=new PriorityQueue<Paciente>();
    }

    public void encolar (Paciente dato){
        clinica.add(dato);
    }

    public Paciente atentido () throws Exception {
        if (clinica.isEmpty()){
            throw new Exception("No quedan elemetos!");
        }
        return clinica.poll();
        //clinica.remove es lo mismo.
        //Ave
    }

    public List<Paciente> listarPacientes(){
        return clinica.stream().collect(Collectors.toList());
        //return clinica.stream.toList();
    }


}
