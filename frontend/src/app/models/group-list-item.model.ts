export interface GroupListItemModel{

    id: number;
    name: string;
    owner: string;
    imgUrl: string;
    events: number;
    posts: number;
    currentUserTheOwner?: boolean;

}
