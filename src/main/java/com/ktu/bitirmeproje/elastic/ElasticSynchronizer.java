//package com.ktu.bitirmeproje.elastic;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import com.ktu.bitirmeproje.business.dto.prod.ProductDto;
//import com.ktu.bitirmeproje.data.entity.prod.PointsOfProduct;
//import com.ktu.bitirmeproje.data.entity.prod.Product;
//import com.ktu.bitirmeproje.data.entity.prod.ProductImages;
//import com.ktu.bitirmeproje.data.repository.ProductElasticRepository;
//import com.ktu.bitirmeproje.data.repository.ProductRepository;
//import com.ktu.bitirmeproje.utils.CategoryType;
//
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.Expression;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//import javax.transaction.Transactional;
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Service
//public class ElasticSynchronizer {
//
//    private ProductRepository userDAO;
//    private ProductElasticRepository userESRepo;
//    //private UserMapper userMapper;
//
//    private static final Logger LOG = LoggerFactory.getLogger(ElasticSynchronizer.class);
//
//    @Autowired
//    public ElasticSynchronizer(ProductRepository userDAO, ProductElasticRepository userESRepo) {
//        this.userDAO = userDAO;
//        this.userESRepo = userESRepo;
//    }
//
//    @Scheduled(cron = "0 */3 * * * *")
//    @Transactional
//    public void sync() {
//        LOG.info("Start Syncing - {}", LocalDateTime.now());
//        this.syncUsers();
//        LOG.info(" End Syncing - {}", LocalDateTime.now());
//    }
//
//    private void syncUsers() {
//
//        Specification<Product> userSpecification = (root, criteriaQuery, criteriaBuilder) ->
//                getModificationDatePredicate(criteriaBuilder, root);
//        Iterable<Product> userList;
//        if (userESRepo.count() == 0) {
//            userList = userDAO.findAll();
//        } else {
//           // userList = userDAO.findAll(userSpecification);
//        }
//        for(Product user: userList) {
//            LOG.info("Syncing User - {}", user.getProductID());
//            userESRepo.save(this.userMapper.toUserModel(user));
//        }
//    }
//
//    private static Predicate getModificationDatePredicate(CriteriaBuilder cb, Root<?> root) {
//        Expression<Timestamp> currentTime;
//        currentTime = cb.currentTimestamp();
//        Expression<Timestamp> currentTimeMinus = cb.literal(new Timestamp(System.currentTimeMillis() -
//                (Constants.INTERVAL_IN_MILLISECONDE)));
//        return cb.between(root.<Date>get(Constants.MODIFICATION_DATE),
//                currentTimeMinus,
//                currentTime
//        );
//    }
//	private void convertToDto(Product product, ProductDto productDto) {
//		productDto.setId(product.getProductID());
//		productDto.setPrice(product.getPrice());
//		productDto.setBrand(product.getBrand());
//		productDto.setModel(product.getModel());
//		productDto.setDate(product.getDate());
//		productDto.setStoreNickName(product.getStore().getNickName());
//		productDto.setCategory(CategoryType.valueOf(product.getCategory()));
//		productDto.setFeatures(product.getFeatures());
//		productDto.setUnits(product.getUnits());
// 	
//		
// 	}
//}