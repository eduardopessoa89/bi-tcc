cube(`Itemvenda`, {
  sql: `SELECT * FROM public.itemvenda`,
  
  preAggregations: {
    // Pre-Aggregations definitions go here
    // Learn more here: https://cube.dev/docs/caching/pre-aggregations/getting-started  
  },
  
  joins: {
    Produto: {
      sql: `${CUBE}.produto_id = ${Produto}.id`,
      relationship: `belongsTo`
    },
    
    Venda: {
      sql: `${CUBE}.venda_id = ${Venda}.id`,
      relationship: `belongsTo`
    }
  },
  
  measures: {
    count: {
      type: `count`,
      drillMembers: [id]
    }
  },
  
  dimensions: {
    id: {
      sql: `id`,
      type: `number`,
      primaryKey: true
    },
    
    deleted: {
      sql: `deleted`,
      type: `string`
    }
  },
  
  dataSource: `default`
});
