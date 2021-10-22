export class SecurityUtil {

    static getPermissionRead(context: string): string[] {
        return SecurityUtil.makePermission('read', context);
    }

    static getPermissionInsert(context: string): string[] {
        return SecurityUtil.makePermission('insert', context);
    }

    static getPermissionUpdate(context: string): string[] {
        return SecurityUtil.makePermission('update', context);
    }

    static getPermissionDelete(context: string): string[] {
        return SecurityUtil.makePermission('delete', context);
    }
    
    static getPermissionAssociate(context: string): string[] {
        return SecurityUtil.makePermission('associate', context);
    }

    private static makePermission(operation: string, context: string): string[] {
        return [`${operation}-*`, `${operation}-${context}`];
    }

}