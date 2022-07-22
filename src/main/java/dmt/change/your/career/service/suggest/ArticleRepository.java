package dmt.change.your.career.service.suggest;

import java.util.List;

import dmt.change.your.career.service.suggest.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query(nativeQuery = true, value = "" +
            "select * " +
            "from article " +
            "where id in (:ids) "
    )
    List<Article> findAllArticlesByIds(List<Long> ids);

    List<Article> findAllByMlSendIsFalse();

    @Modifying
    @Query(nativeQuery = true, value = "" +
            "update article " +
            "set ml_send = true " +
            "where id = :id "
    )
    void setArticleMlSendTrue(long id);
}
