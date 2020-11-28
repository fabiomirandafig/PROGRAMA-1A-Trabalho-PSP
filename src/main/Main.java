package main;

import java.util.Scanner;
import java.lang.Math;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        principal(new Scanner(System.in));
    }

    public static void principal(Scanner p){
        int Total,Minimo,Pontuacao,Classificados;
        double DesvioCla,MediaCla,DesvioComp,MediaComp;

        Total = Integer.parseInt(p.next());/*entrada do número total de competidores*/
        Minimo = Integer.parseInt(p.next());/*entrada do número mínimo de classificados*/
        
        int[] Pontuacoes = new int[Total];
        for (int i = 0; i < Total; i++) {/*entrada da pontuação de todos os competidores*/
            Pontuacao = Integer.parseInt(p.next());
            Pontuacoes[i] = Pontuacao;
        }

        Classificados = NumerodeClassificados(Total,Minimo,Pontuacoes);/*número de classificados para a próxima fase*/
        MediaComp = MediaCompetidores(Total,Pontuacoes);/*média aritmética da pontuação de todos os competidores*/
        DesvioComp = DesvioCompetidores(Total,Pontuacoes,MediaComp);/*desvio padrão da pontuação de todos os competidores*/
        MediaCla = MediaClassificados(Classificados,Pontuacoes);/*média aritmética da pontuação dos classificados*/
        DesvioCla = DesvioClassificados(Classificados,Pontuacoes,MediaCla);/*desvio padrão da pontuação dos classificados*/

        DecimalFormat d = new DecimalFormat("#,###0.00", new DecimalFormatSymbols(new Locale("en", "US")));/*formato .00*/
        System.out.println(Classificados);
        System.out.println(d.format(DesvioCla));
        System.out.println(d.format(MediaCla));
        System.out.println(d.format(DesvioComp));
        System.out.println(d.format(MediaComp));
    }

    public static int NumerodeClassificados(int T,int M, int[] P){/*Retona o número de classificados para a próxima fase*/
        for (int i = 1; i < T; i++) {/*Ordena o vetor Pontuacoes de forma decrescente - maior para o menor*/
            int elemInserir = P[i];
            int j = i-1;
            while(j >= 0 && P[j] < elemInserir) {
                P[j+1] = P[j];
                j--;
            }
            P[j+1] = elemInserir;
        }

        if(T == 1 || P[M-1]!=P[M])/*Somente um competidor ou não há empate na ultima vaga de classificação*/
            return M;
        for (int i = M; i < T; i++){/*Quando ocorre empate na ultima vaga de classificação*/
            if(P[i-1]!=P[i]){
                return i;
            } 
        }
        return T;/*Todos os competidores foram classificados para a próxima fase pois todos apresentam mesma pontuação*/
    }

    public static double MediaCompetidores(int T, int[] P){/*Retorna média aritmética da pontuação de todos competidores*/
        double total,media;
        total=0;
        for(int i = 0; i < T; i++){
            total = total + P[i]; 
        }
        return media = total/T;
    }

    public static double DesvioCompetidores(int T,int[] P,double M){/*Retorna desvio padrão de todos os competidores*/
        double total,desvio;
        double[] subtracoes = new double[T];
        for(int i = 0; i < T; i++){
            subtracoes[i] = Math.pow(P[i] - M,2);
        }
        total = desvio = 0;
        for(int i = 0; i < T; i++){
            total = total + subtracoes[i];
        }
        return desvio = Math.sqrt(total/T);
    }

    public static double MediaClassificados(int C,int[] P){/*Retorna média aritmética da pontuação dos classificados*/
        double total,media;
        total=0;
        for(int i = 0; i < C; i++){
            total = total + P[i]; 
        }
        return media = total/C;
    } 

    public static double DesvioClassificados(int C,int[] P, double M){/*Retorna desvio padrão dos classificados*/
        double total,desvio;
        double[] subtracoes = new double[C];
        for(int i = 0; i < C; i++){
            subtracoes[i] = Math.pow(P[i] - M,2);
        }
        total = desvio = 0;
        for(int i = 0; i < C; i++){
            total = total + subtracoes[i];
        }
        return desvio = Math.sqrt(total/C);
    }
}