package excluirbancodados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ExcluirBancoDados {
    static final String banco = "jdbc:mysql://localhost:3306/agenciaviagens";

    public static void main(String[] args) {
        Connection conexao = null;
        Statement consulta = null;
        ResultSet resultados = null;
        PreparedStatement minhainclusao = null;

        try {
            conexao = DriverManager.getConnection(banco, "root", "");
            consulta = conexao.createStatement();
            resultados = consulta.executeQuery("SELECT * FROM turista");

            ResultSetMetaData colunas = resultados.getMetaData();
            int numeroColunas = colunas.getColumnCount();

            System.out.println("Informações do Banco de Dados:");
            for (int i = 1; i <= numeroColunas; i++) {
                System.out.print(colunas.getColumnName(i) + "\t");
            }
            System.out.println();

            while (resultados.next()) {
                for (int i = 1; i <= numeroColunas; i++) {
                    System.out.print(resultados.getString(i) + "\t");
                }
                System.out.println();
            }

            String codigoStr = JOptionPane.showInputDialog(null, "Informe o código do turista a ser excluído:");

            if (codigoStr == null || codigoStr.trim().isEmpty()) {
                System.out.println("Nenhum código informado.");
                return;
            }

            int codigo = Integer.parseInt(codigoStr.trim());

            minhainclusao = conexao.prepareStatement("DELETE FROM turista WHERE codigo = ?");
            minhainclusao.setInt(1, codigo);

            int linhasAfetadas = minhainclusao.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Turista excluído com sucesso!");
            } else {
                System.out.println("Nenhum turista encontrado com esse código.");
            }

        } catch (SQLException erro) {
            erro.printStackTrace();
        } catch (NumberFormatException erroNumero) {
            System.out.println("Código inválido! Digite um número.");
        } finally {
            try {
                if (resultados != null) resultados.close();
                if (consulta != null) consulta.close();
                if (minhainclusao != null) minhainclusao.close();
                if (conexao != null) conexao.close();
            } catch (SQLException erronovo) {
                erronovo.printStackTrace();
            }
        }
    }
}