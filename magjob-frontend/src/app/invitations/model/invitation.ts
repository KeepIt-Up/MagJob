import { User } from 'src/app/user/model/user';
import { Organization } from './../../organization/model/organization';
export interface Invitation {
    organization: Organization,
    user: User
}
