package demo.config;

import demo.pojo.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
public class CsvBatchJobConfig {
    @Autowired
    JobBuilderFactory jobBuilderFactory; //用来构建Job
    @Autowired
    StepBuilderFactory stepBuilderFactory; //用来构建Step
    @Autowired
    DataSource dataSource; //用来支持持久化操作

    @Bean
    @StepScope
    FlatFileItemReader<User> itemReader() { //配置数据的写入逻辑
        FlatFileItemReader<User> reader = new FlatFileItemReader<>();
        reader.setLinesToSkip(1); //因为文件的第一行是标题，所以要跳过第一行
        reader.setResource(new ClassPathResource("data.csv")); //要读取的文件的位置
        reader.setLineMapper(new DefaultLineMapper<User>(){{  //设置文件每一行记录的映射
            setLineTokenizer(new DelimitedLineTokenizer(){{
                setNames("id", "username", "address", "gender");
                setDelimiter(",");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<User>(){{ //映射的实体属性
                setTargetType(User.class);
            }});
        }});
        return reader;
    }

    @Bean
    JdbcBatchItemWriter jdbcBatchItemWriter() { //配置数据的写出逻辑
        JdbcBatchItemWriter writer = new JdbcBatchItemWriter();
        writer.setDataSource(dataSource);
        writer.setSql("insert into user(id, username, address, gender) " +
                "values(:id, :username, :address, :gender)");
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider());
        return writer;
    }

    @Bean
    Step csvStep() { //配置一个Step
        return stepBuilderFactory.get("csvStep")
                .<User, User>chunk(2) //每读取两条记录，就进行一次写入
                .reader(itemReader())
                .writer(jdbcBatchItemWriter())
                .build();
    }

    @Bean
    Job csvJob() { //配置一个Job
        return jobBuilderFactory.get("csvJob")
                .start(csvStep())
                .build();
    }
}
