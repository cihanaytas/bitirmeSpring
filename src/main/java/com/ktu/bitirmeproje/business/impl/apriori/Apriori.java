package com.ktu.bitirmeproje.business.impl.apriori;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ktu.bitirmeproje.data.entity.prod.AprioriOran;
import com.ktu.bitirmeproje.data.repository.AprioriOranRepository;
import com.ktu.bitirmeproje.utils.CategoryType;

@Service
public class Apriori {


	@Autowired
	private AprioriOranRepository apRep;
    

//    List<String> products= new ArrayList<String>();
//    products.add("telefon");
//    products.add("tablet");
//    products.add("kulaklık");
//    products.add("ayakkabı");
//    products.add("telefon kabı");
//    products.add("televizyon");
//    products.add("laptop");


	
    @Async
    public void fun() throws IOException {
        BufferedReader reader;
        List<List<String>> myList = new ArrayList<List<String>>();
        HashMap<String,Double> oranlar = new HashMap<String,Double>();
        List<List<String>> myList2 = new ArrayList<List<String>>();
        List<List<String>> myList3 = new ArrayList<List<String>>();
       // List<String> products= new ArrayList<String>();
        
        List<String> products = Stream.of(CategoryType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        
 
        
    
//    products.add("ekmek");
//    products.add("süt");
//    products.add("çerez");
//    products.add("salça");
//    products.add("bira");
//    products.add("bebek bezi");
//    products.add("yumurta");
//    products.add("kola");



    //alışverişler dosyadan alınıp listeye dönüştürülüyor
    reader = new BufferedReader(new FileReader("C:\\Users\\cihanaytas\\Desktop\\ccc.txt"));
    String line = reader.readLine();
    while (line!=null){
        List<String> a = new ArrayList<String>();
        String[] tokens = line.split(",");
        for(int i=0; i<tokens.length; i++){
            a.add(tokens[i]);
        }
        myList.add(a);
        line = reader.readLine();
    }

    //  System.out.println(myList);



    //belirlediğimiz oranın üstünde sepette bulunan ürünler yeni bir hashmap listesine ekleniyor
    for(String product : products){
        double count = 0;
        for(List<String> a : myList){
            if(a.contains(product)){
                count++;
            }
        }
        double oran = count/myList.size();
      //  if(oran>0.2){
            oranlar.put(product,oran);
        //}

    }

  //  System.out.println(oranlar);




    List<String> p2= new ArrayList<String>(oranlar.keySet());
    String[] products2 = p2.toArray(new String[0]);

    HashMap<Object, Double> o2 = new HashMap<Object, Double>();


    //ikili  şekilde bulunmalarına bakılıyor ve hashmape kaydediliyor
    for(int i=0; i<products2.length; i++){
        for(int j=i+1; j<products2.length; j++){
            double count = 0;
            //System.out.println(products2[i]+ "-" +products2[j]);
            for(List<String> a : myList){
                if(a.contains(products2[i]) && a.contains(products2[j])){
                     count++;
                }
            }
            double p = count/myList.size();
           // System.out.println(count/myList.size());
            double ikiliOran = p/oranlar.get(products2[i]);

           // System.out.println(ikiliOran);
            if(ikiliOran>=0.2) {
            MyKeyClass o1 = new MyKeyClass();
            o1.setKey1(products2[i]);
            o1.setKey2(products2[j]);
            o2.put(o1,ikiliOran);                     
            }
            
            ikiliOran = p/oranlar.get(products2[j]);
            if(ikiliOran>=0.2) {
            MyKeyClass o5 = new MyKeyClass();
            o5.setKey1(products2[j]);
            o5.setKey2(products2[i]);
            o2.put(o5,ikiliOran);
            }
        }

    }



    //oranların yazılı olduğu dosyadan key-key-oran şeklinde listeden okuma yapılıyor

    reader = new BufferedReader(new FileReader("C:\\Users\\cihanaytas\\Desktop\\filename.txt"));
    String line2 = reader.readLine();
    while (line2!=null){
        List<String> a = new ArrayList<String>();
        String[] tokens = line2.split(",");
        for(int i=0; i<tokens.length; i++){
            a.add(tokens[i]);
        }
        myList2.add(a);
        line2 = reader.readLine();
    }

  //  System.out.println(myList2);



    //oranları bir dosyaya kaydediyoruz
    try {
        File fout = new File("C:\\Users\\cihanaytas\\Desktop\\filename.txt");
        FileOutputStream fos = new FileOutputStream(fout);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        Iterator iterator = o2.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry me2 = (Map.Entry) iterator.next();
            MyKeyClass mkc = (MyKeyClass) me2.getKey();
            bw.write(mkc.getKey1());
            bw.write(",");
            bw.write(mkc.getKey2());
            bw.write(",");
            bw.write(String.valueOf(me2.getValue()));
            bw.newLine();
         //   System.out.println("Key: "+mkc.getKey1() + " Key: "+mkc.getKey2() + " & Value: " + me2.getValue());
        }

        bw.close();
       // System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }



    //yeni oranların yazılı olduğu dosyadan key-key-oran şeklinde listeden okuma yapılıyor

    reader = new BufferedReader(new FileReader("C:\\Users\\cihanaytas\\Desktop\\filename.txt"));
    String line3 = reader.readLine();
    while (line3!=null){
        List<String> a = new ArrayList<String>();
        String[] tokens = line3.split(",");
        for(int i=0; i<tokens.length; i++){
            a.add(tokens[i]);
        }
        myList3.add(a);
        line3 = reader.readLine();
               
    }
    


  // 	System.out.println(myList3);
	System.out.println(myList2);

    List<List<String>> myList4 = new ArrayList<List<String>>(myList3);
    	myList4.removeAll(myList2);
    	myList2.removeAll(myList3);
    	//myList2.removeAll(myList4);

    	System.out.println(myList2);
    	//System.out.println(myList3);
     //	System.out.println(myList4);
     	
     	
    
    
    for(List<String> list : myList4) {
    	if(apRep.exist(list.get(0),list.get(1))>0) {
    		AprioriOran ap = apRep.getExist(list.get(0),list.get(1));
        	ap.setOran(list.get(2));
        	apRep.save(ap);
    		
    	}else {
        	AprioriOran ap = new AprioriOran();
        	ap.setCategory1(list.get(0));
        	ap.setCategory2(list.get(1));
        	ap.setOran(list.get(2));
        	apRep.save(ap);
    	}

    }
    
    
    for(List<String> list : myList2) {
    	AprioriOran ap = apRep.getExist2(list.get(0),list.get(1),list.get(2));
    	if(ap!=null) {
    		System.out.println(ap);
    		apRep.deleteById(ap.getId());
    	}
    }


    }
}
