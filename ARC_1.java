import java.util.ArrayList;
import java.util.Scanner;
class Kontrol
{
    boolean kontrol;
    String madde;
    public Kontrol(String madde)
    {
        this.madde=madde;
        this.kontrol=false;

    }
    public void kontrol_et()
    {
        kontrol=true;
    }
    public String toString()
    {
        return madde+(kontrol?" kontrol edildi.":" kontrol edilmedi.");
    }
}
class Pil 
{
    double voltaj;
    int num;
    String saglik;
    public Pil(double voltaj, int num, String saglik)
    {
        this.voltaj=voltaj;
        this.num=num;
        this.saglik=saglik;
    }
    public String toString()
    {
        return voltaj+"V voltaja sahip "+saglik+" sağlıklı akü";
    }
}
public class Main
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        ArrayList<Kontrol> kontrol_list = new ArrayList<Kontrol>();
        ArrayList<Pil> piller = new ArrayList<Pil>();
        while(true)
        {
            System.out.println("1, 2 veya 3 giriniz");
            System.out.println("1- Madde ekleme");
            System.out.println("2- Kontrol etme");
            System.out.println("3- Çıkış");
            String secim = input.nextLine();
            if(secim.equals("1"))
            {
                System.out.println("Eklemek için bir madde giriniz: ");
                String madde1 = input.nextLine();
                kontrol_list.add(new Kontrol(madde1));
            }
            else if(secim.equals("2"))
            {
                if(kontrol_list.size()==0)
                {
                    System.out.println("Kontrol için hiçbir madde bulunmamaktadır. Lütfen bir madde giriniz. ");
                    continue;
                }
                for(Kontrol madde:kontrol_list)
                {
                    System.out.println(madde);
                    boolean sart=true;
                    while(sart)
                    {
                        System.out.println("Bu madde kontrol edildi mi? (evet/hayır):");
                        String edildi_mi = input.nextLine();
                        if(!(edildi_mi.equals("evet")||edildi_mi.equals("hayır")))
                        {
                            System.out.println("Lütfen geçerli bir girdi girin. ");
                            continue;
                        }
                        else if(edildi_mi.equals("evet"))
                        {
                            madde.kontrol_et();
                        }
                        sart=false;
                    }
                }
                System.out.println("Kaç tane akü gireceğinizi giriniz.");
                int aku_num=input.nextInt();
                input.nextLine();
                for(int i=0;i<aku_num;i++)
                {
                    System.out.println("Akünün voltajını giriniz: ");
                    double voltaj1 = input.nextDouble();
                    input.nextLine();
                    System.out.println("Akünün sağlığını (ortalama/iyi) giriniz: ");
                    String saglik1 = input.nextLine();
                    int num1=i+1;
                    piller.add(new Pil(voltaj1,num1,saglik1));
                }
                Pil eniyiaku = eniyipil(piller);
                System.out.println("Maç için en uygun akü: " + eniyiaku);

                boolean tamamlandı = true;
                for (Kontrol madde : kontrol_list) 
                {
                    if (!madde.kontrol) 
                    {
                        tamamlandı = false;
                        System.out.println("Tekrar kontrol etmeniz gereken madde: " + madde);
                    }
                }

                if (tamamlandı) 
                {
                    System.out.println("Maça Hazır!");
                    break;
                } else 
                {
                    System.out.println("Bazı maddeler kontrol edilmedi, lütfen tekrar kontrol edin. ");
                }
            }
            else if(secim.equals("3"))
            {
                System.out.println("Çıkış yapıldı.");
                break;
            }
            else
            {
                System.out.println("Geçersiz girdi girdiniz. Lütfen geçerli bir girdi giriniz.");
            }

        }
        input.close();
    }
    private static Pil eniyipil(ArrayList<Pil> piller) 
    {
        Pil eniyibatarya=piller.get(0);

        for(Pil pil:piller)
        {
            if(Math.abs(13-eniyibatarya.voltaj)>Math.abs(13-pil.voltaj))
            {
                eniyibatarya=pil;
            }
            else if(Math.abs(13-eniyibatarya.voltaj)==Math.abs(13-pil.voltaj))
            {
                if(eniyibatarya.saglik.equals("ortalama")&&pil.saglik.equals("iyi"))
                {
                    eniyibatarya=pil;
                }
            }
        }
        return eniyibatarya;
    }
}
