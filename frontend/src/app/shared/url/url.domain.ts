'use strict';

export const SERVER_URL = 'http://' + document.location.hostname + ':8080/businessSaleProject';

// tslint:disable-next-line: no-namespace
export namespace UsuarioURL {
    export const BASE = 'usuarios';
}

     // tslint:disable-next-line: no-namespace
export namespace VendaURL {
    export const BASE = 'vendas';
}

   // tslint:disable-next-line: no-namespace
export namespace ItemVendaURL {
    export const BASE = 'itensvenda';
}

  // tslint:disable-next-line: no-namespace
export namespace ProdutoURL {
    export const BASE = 'produtos';
}

   // tslint:disable-next-line: no-namespace
export namespace PapelURL {
    export const BASE = 'papeis';
}

  // tslint:disable-next-line: no-namespace
export namespace PapelPermissaoURL {
    export const BASE = 'papeis-permissoes';
    export const MULTIPLE = BASE + '/multiple';
} // tslint:disable-next-line: no-namespace
export namespace PermissaoURL {
    export const BASE = 'permissoes';
}

   

// tslint:disable-next-line: no-namespace
export namespace LoginURL {
    export const BASE = 'login';
    export const VERIFY_TOKEN = 'verify';
}