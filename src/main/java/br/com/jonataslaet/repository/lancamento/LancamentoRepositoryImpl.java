package br.com.jonataslaet.repository.lancamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import br.com.jonataslaet.model.Lancamento;
import br.com.jonataslaet.repository.filter.LancamentoFilter;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
		
		Root<Lancamento> root = criteria.from(Lancamento.class);
		
		Predicate[] restricoes = getRestricoes(lancamentoFilter, builder, root);
		criteria.where(restricoes);
		
		TypedQuery<Lancamento> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] getRestricoes(LancamentoFilter lancamentoFilter, CriteriaBuilder builder,
			Root<Lancamento> root) {
		List<Predicate> restricoes = new ArrayList<>();
		
		if (!StringUtils.isEmpty(lancamentoFilter.getDescricao())) {
			restricoes.add(builder.like(builder.lower(root.get("descricao")), "%"+lancamentoFilter.getDescricao().toLowerCase()+"%"));
		}
		
		if (lancamentoFilter.getDataVencimentoAte() != null) {
			restricoes.add(builder.lessThanOrEqualTo(root.get("dataVencimento"), lancamentoFilter.getDataVencimentoAte()));
		}
		
		if (lancamentoFilter.getDataVencimentoDe() != null) {
			restricoes.add(builder.greaterThanOrEqualTo(root.get("dataVencimento"), lancamentoFilter.getDataVencimentoDe()));
		}
		
		return restricoes.toArray(new Predicate[restricoes.size()]);
	}

}
