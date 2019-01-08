
apres le deploiment certain l'adresse du serveur d'application devrai etre changer dans les fichier:
1.DetailinspectionController http://localhost:8080/ par l'adresse du serveur source


                <p:graphicImage id="j_idt105"  name="/images/preloader.gif"  style="width: 32px;"/>
System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(currentDate));
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, 5);//a changer apres
        Date currentDatePlus = c.getTime();
        System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(currentDatePlus));