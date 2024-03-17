import {Drawer, List, ListItemButton, ListItemIcon, ListItemText} from "@mui/material";
import React, {useState} from 'react';
import MenuIcon from "@mui/icons-material/Menu";
import IconButton from "@mui/material/IconButton";

function DrawerSB() {
    const [openDrawer, setOpenDrawer] = useState(false);
    const PAGES = ["Home", "Articles", "Create Article", "Account","Login", "Sign up"];
    return (
        <React.Fragment>
            <Drawer open={openDrawer} onClose={() => setOpenDrawer(false)}>
                <List>
                    {PAGES.map((page, index) =>(
                        <ListItemButton key={index}>
                            <ListItemIcon>
                                <ListItemText> {page}</ListItemText>
                            </ListItemIcon>
                        </ListItemButton>
                    ))}

                </List>
            </Drawer>
            <IconButton
                size="large"
                edge="start"
                color="inherit"
                aria-label="menu"
                onClick={()=> setOpenDrawer(!openDrawer)}
                sx={{ marginLeft: "auto"}}
            >
                <MenuIcon />
            </IconButton>
        </React.Fragment>
    )
}
export default DrawerSB;