package util;

public class StopWatch {
    //Inserir tempo em milisegundos
    public static void elapseTime(double milis) {
        //Tempo inicial da contagem
        double startTime = System.currentTimeMillis();
        //Tempo final da contagem
        double stopTime = System.currentTimeMillis() + milis;
        //Tempo que se passou
        double elapsed = startTime;
        /*Apenas quando o tempo determinado em milisegundos tiver passado 
          retorna a execução para a função principal*/
        while(true) {
            elapsed = stopTime - elapsed;
            if(elapsed > 0) {
                elapsed = System.currentTimeMillis();
            }
            else if(elapsed < 0)
                return;
        }
    }
}
