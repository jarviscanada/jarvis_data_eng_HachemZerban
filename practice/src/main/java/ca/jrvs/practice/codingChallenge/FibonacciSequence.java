package ca.jrvs.practice.codingChallenge;

public class FibonacciSequence {

  public static void main(String[] args) {

    System.out.println(dynamicFiboSeque(6));
  }
  public static int  Fibon(int n) {
    if (n==0)
      return 0 ;
    if (n ==1 || n==2)
      return 1;

    return Fibon(n-2)+Fibon(n-1);

  }

  public static int dynamicFiboSeque(int n) {

    int [] tab = new int[n+1];
    tab[0]=0;
    tab[1]=1;

    for (int i=2;i<=n;i++) {
      tab[i]=tab[i-1]+tab[i-2];

    }
    return tab[n];

  }


}
