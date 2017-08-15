package yolung.cardinalblue.com.mvp_pattern.graphql;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

import graphql.GraphQL;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import java.util.Map;

/**
 * Created by luyolung on 05/06/2017.
 */

public class GraphQLTest {
    public void test(){
        GraphQLObjectType queryType = newObject()
            .name("helloWorldQuery")
            .field(newFieldDefinition()
                .type(GraphQLString)
                .name("hello")
                .staticValue("world"))
            .build();

        GraphQLSchema schema = GraphQLSchema.newSchema()
            .query(queryType)
            .build();

        GraphQL graphQL = GraphQL.newGraphQL(schema).build();

        Map<String, Object> result = graphQL.execute("{hello}").getData();
        System.out.println(result);
    }
}
