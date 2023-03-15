package infra;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.batmanvascaino.entidades.Jogador;
import com.batmanvascaino.entidades.Times;

public class DAO<E> {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Class<E> classe;
	
	static {
		try {
			emf = Persistence.createEntityManagerFactory("times-jpa");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public DAO(Class<E> classe) {
		this.classe = classe;
		em = emf.createEntityManager();
	}
	
	public DAO() {
		this(null);
	}
	
	public DAO<E> abrirT(){
		em.getTransaction().begin();
		return this;
	}
	
	public DAO<E> fecharT(){
		em.getTransaction().commit();
		return this;
	}
	
	public DAO<E> incluir(E entidade){
		em.persist(entidade);
		return this;
	}
	
	public DAO<E> incluirAtomico(E entidade){
		return this.abrirT().incluir(entidade).fecharT();
	}
	
	
	public List<E> obterTodos(){
		
		String jqpl = "select e from " + classe.getName() + " e";
		TypedQuery<E> query = em.createQuery(jqpl, classe);
		
		
		return query.getResultList();
	}
	
	
	public void fechar() {
		em.close();
	}
	
	
	public Times obterTimePeloNome(String nome) {
		
		String jqpl = "select t from Times t where t.nome like :nome";
		TypedQuery<Times> query = em.createQuery(jqpl, Times.class);
		query.setParameter("nome", nome);
		
		List<Times> resultList = query.getResultList();
		if (resultList.isEmpty()) {
		    return null;
		}
		
		return resultList.get(0);
	}
	
	
	public DAO<E> removerEntidade(E entidade){
		em.getTransaction().begin();
		em.remove(entidade);
		em.getTransaction().commit();
		
		return this;
	}
	
	public DAO<E> alterarTime(Times time, String nome, String campeonato, String nacionalidade){
		em.getTransaction().begin();
		time.setNome(nome);
		time.setCampeonato(campeonato);
		time.setNacionalidade(nacionalidade);
		em.getTransaction().commit();
		
		return this;
		
		
	}
	
	public DAO<E> alterarJogador(Jogador jogador, String nome, String idade, String valor, String nacionalidade,String posicao, Times Time){
		em.getTransaction().begin();
		jogador.setNome(nome);
		jogador.setIdade(Integer.parseInt(idade));
		jogador.setValor(valor);
		jogador.setNacionalidade(nacionalidade);
		jogador.setPosicao(posicao);
		jogador.setTime(Time);
		em.getTransaction().commit();
		
		return this;
		
		
	}
	
}
