cube(`Venda`, {
  sql: `SELECT * FROM public.venda`,
  
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
    cliente: {
      sql: `cliente`,
      type: `string`
    },
    
    deleted: {
      sql: `deleted`,
      type: `string`
    },
    
    id: {
      sql: `id`,
      type: `number`,
      primaryKey: true
    },
    
    datavenda: {
      sql: `datavenda`,
      type: `time`
    },
    
    dataVenda: {
      sql: `data_venda`,
      type: `time`
    }
  },
  
  dataSource: `default`
});
