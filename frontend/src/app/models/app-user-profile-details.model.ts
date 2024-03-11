export interface AppUserProfileDetailsModel {
  id:number;
  name: string;
  username: string;
  email: string;
  role: string;
  memberInGroups: number;
  ownedGroups: number;
  inRelationWithMembers: number;
  imgUrl: string;
}
