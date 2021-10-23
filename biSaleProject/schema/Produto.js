cube(`Produto`, {
  sql: `SELECT * FROM public.produto`,
  
  preAggregations: {
    // Pre-Aggregations definitions go here
    // Learn more here: https://cube.dev/docs/caching/pre-aggregations/getting-started  
  },
  
  joins: {
    
  },
  
  measures: {
    count: {
      type: `count`,
      drillMembers: [id]
    }
  },
  
  dimensions: {
    deleted: {
      sql: `deleted`,
      type: `string`
    },
    
    id: {
      sql: `id`,
      type: `number`,
      primaryKey: true
    },
    
    nome: {
      sql: `nome`,
      type: `string`
    },
    
    categoria: {
      sql: `categoria`,
      type: `string`
    }
  },
  
  dataSource: `default`
});
