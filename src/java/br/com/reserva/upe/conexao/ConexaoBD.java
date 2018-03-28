package br.com.reserva.upe.conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConexaoBD {
    
    private static Connection com = null;
    
    private static final String URL =  "jdbc:mysql://localhost:3306/";
    //Usuário do banco de dado localhost:
    private static final String USUARIO = "root";
    //Senha do banco de dado localhost
    private static final String SENHA = "1234";
    //Nome padão do banco de dado da aplicação:
    private static final String NOME_BANCO = "reserva_upe";

    public static Connection Conectar(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            com = DriverManager.getConnection(URL+NOME_BANCO, USUARIO, SENHA);
            System.out.println("Conectado Sucesso!");
            return com;
        } catch (ClassNotFoundException ex) {
            System.out.print("Driver não encontrado.");
        } catch (SQLException e) {
            criarBanco();
            System.out.print(">>> [Error Conexão ao sql!]\n"+e+"\nTente novamente!\nVerificando banco e tabelas...\nFinish.");
        }
        return null;    
    }

    public static void Desconectar() {
        try {
            com.close();
            System.out.print(">>> Conexão Encerrada com sucesso!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Método para cria banco e as tabelas do banco automaticamente.
     */
    private static void criarBanco(){
        if(!DBexiste(NOME_BANCO)){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                com = DriverManager.getConnection(URL, USUARIO, SENHA);
                Statement st = com.createStatement();
                st.executeUpdate("CREATE DATABASE IF NOT EXISTS "+NOME_BANCO);
                criarTabelaPessoa();
                criarTabelaReserva();
                criarTabelaHorario();
            } catch (Exception e) {
                System.out.print("Erro criar banco: "+e);
            }
        }
    }
    
    private static void criarTabelaPessoa() throws SQLException{
        com = Conectar();
        Statement st = com.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS "+NOME_BANCO+".pessoa(\n" +
                    "id int auto_increment, \n" +
                    "nome varchar(70), \n" +
                    "email varchar(60),\n" +
                    "senha varchar(30), \n" +
                    "tipo varchar(9), \n" +
                    "primary key(id),\n" +
                    "unique(email));";
        st.executeUpdate(sql);
        String sqlAdmin = "INSERT INTO "+NOME_BANCO+".pessoa (nome, email, senha, tipo) VALUES ('Admin','upe@upe.br','1234','2');";
        st.executeUpdate(sqlAdmin);
    }
    
    private static void criarTabelaReserva() throws SQLException{
        Statement st = com.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS "+NOME_BANCO+".reserva(\n" +
                    "id int auto_increment, \n" +
                    "idPessoa int, \n" +
                    "dataDaReserva varchar(30) not null,\n" +
                    "turno varchar(30), \n" +
                    "horario varchar(30),\n" +
                    "laboratorio varchar(30),\n" +
                    "descricao varchar(30), \n" +
                    "primary key(id), \n" +
                    "foreign key(idPessoa)\n" +
                    "references pessoa(id));";
        st.executeUpdate(sql);   
    }
    
    private static void criarTabelaHorario() throws SQLException{
        Statement st = com.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS "+NOME_BANCO+".horario(\n" +
                    "id int auto_increment,\n" +
                    "hora varchar(6),\n" +
                    "primary key(id));";
        st.executeUpdate(sql); 
        inserirHoras();
    }
    
    private static void inserirHoras() throws SQLException{
        if(qtdHorarios() == 0){
            try{
            Statement st = com.createStatement();
            for(int i = 0; i <= 5; i++){
                String sql = "INSERT INTO horario (hora) VALUES ('07:"+i+"0');";
                st.executeUpdate(sql);
            }
            for(int i = 0; i <= 5; i++){
                String sql = "INSERT INTO horario (hora) VALUES ('08:"+i+"0');";
                st.executeUpdate(sql);
            }
            for(int i = 0; i <= 5; i++){
                String sql = "INSERT INTO horario (hora) VALUES ('09:"+i+"0');";
                st.executeUpdate(sql);
            }
            for(int i = 0; i <= 5; i++){
                String sql = "INSERT INTO horario (hora) VALUES ('10:"+i+"0');";
                st.executeUpdate(sql);
            }
            for(int i = 0; i <= 5; i++){
                String sql = "INSERT INTO horario (hora) VALUES ('11:"+i+"0');";
                st.executeUpdate(sql);
            }
            for(int i = 0; i <= 5; i++){
                String sql = "INSERT INTO horario (hora) VALUES ('12:"+i+"0');";
                st.executeUpdate(sql);
            }
            for(int i = 0; i <= 5; i++){
                String sql = "INSERT INTO horario (hora) VALUES ('13:"+i+"0');";
                st.executeUpdate(sql);
            }
            for(int i = 0; i <= 5; i++){
                String sql = "INSERT INTO horario (hora) VALUES ('14:"+i+"0');";
                st.executeUpdate(sql);
            }
            for(int i = 0; i <= 5; i++){
                String sql = "INSERT INTO horario (hora) VALUES ('15:"+i+"0');";
                st.executeUpdate(sql);
            }
            for(int i = 0; i <= 5; i++){
                String sql = "INSERT INTO horario (hora) VALUES ('16:"+i+"0');";
                st.executeUpdate(sql);
            }
            for(int i = 0; i <= 5; i++){
                String sql = "INSERT INTO horario (hora) VALUES ('17:"+i+"0');";
                st.executeUpdate(sql);
            }
            for(int i = 0; i <= 5; i++){
                String sql = "INSERT INTO horario (hora) VALUES ('18:"+i+"0');";
                st.executeUpdate(sql);
            }
            for(int i = 0; i <= 5; i++){
                String sql = "INSERT INTO horario (hora) VALUES ('19:"+i+"0');";
                st.executeUpdate(sql);
            }
            for(int i = 0; i <= 5; i++){
                String sql = "INSERT INTO horario (hora) VALUES ('20:"+i+"0');";
                st.executeUpdate(sql);
            }
            for(int i = 0; i <= 5; i++){
                String sql = "INSERT INTO horario (hora) VALUES ('21:"+i+"0');";
                st.executeUpdate(sql);
            }
            String sql = "INSERT INTO horario (hora) VALUES ('22:00');";
            st.executeUpdate(sql);
            
            }catch(SQLException e){
                System.out.print("Erro Método inserirHoras():\n"+e);
            }
        }
        com.close();  
    }
    
    /**
     * Retorna quantidade de registros na tabela horário.
     * @return 
     */
    private static int qtdHorarios(){
        int qtd = 0;
        try {
            String sql = "SELECT COUNT(id) AS qtd FROM horario";
            //com = Conectar();
            Statement st = com.createStatement();
            ResultSet r = st.executeQuery(sql);
            while(r.next()) {
                qtd = Integer.parseInt(r.getString("qtd"));
            }
            return qtd;
        } catch (SQLException e) {
            System.out.print("Erro método qtdHorarios:\n"+e);
        }
        return 0;
    }
    
    /**
     * 
     * @param nomeBD passamos o nome padrão do banco e mesmo
     * @return um valor boolean.
     */
    private static boolean DBexiste(String nomeBD) {
        List<String> bancos = new ArrayList<String>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            com = DriverManager.getConnection(URL, USUARIO, SENHA);
            Statement st = com.createStatement();
            DatabaseMetaData meta = com.getMetaData();
            ResultSet rs = meta.getCatalogs();
            while (rs.next()) {
                String listaBanco = rs.getString("TABLE_CAT");
                bancos.add(listaBanco);
            }
        }catch(SQLException e){
            System.out.print("Erro:\n"+e);
        }catch(Exception e){
            System.out.print("Erro:\n"+e);
        }
        if(bancos.contains(nomeBD)) {
            return true;
        } else {
            return false;
        }
    }

}
